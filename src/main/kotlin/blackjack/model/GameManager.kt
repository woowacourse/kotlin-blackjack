package blackjack.model

import blackjack.state.State

class GameManager(
    private val participants: Participants,
) {
    private val cardDeck = CardDeck()

    fun calculateGameResult(): GameResult {
        val dealerResults = mutableMapOf<Result, Int>()
        val playerResults = mutableMapOf<Player, Result>()

        participants.players.forEach { player ->
            judgeBlackJackScore(
                player = player,
                onResult = { result ->
                    playerResults[player] = result
                    dealerResults[result.reverse()] =
                        dealerResults.getOrDefault(result.reverse(), 0) + ADD_RESULT_COUNT
                },
            )
        }

        return GameResult(dealerResults, playerResults)
    }

    fun setGame() {
        repeat(INIT_HAND_CARD_COUNT) {
            getParticipants().forEach { participant ->
                participant.draw(cardDeck.draw())
            }
        }
    }

    fun returnCardForParticipant(): Card {
        return cardDeck.draw()
    }

    fun getParticipants(): List<Participant> {
        return participants.players + participants.dealer
    }

    fun getDealer(): Dealer {
        return participants.dealer
    }

    fun getAlivePlayers(): List<Participant> {
        return participants.getAlivePlayers()
    }

    private fun judgeBlackJackScore(
        player: Player,
        onResult: (Result) -> Unit,
    ) {
        when (player.getBlackJackState()) {
            is State.Finish.Bust -> onResult(Result.LOSE)
            is State.Finish.BlackJack -> checkBlackJackState(onResult)
            is State.Finish.Stay, State.Action.Hit ->
                compareToResult(
                    player = player,
                    onResult = onResult,
                )
        }
    }

    private fun checkBlackJackState(onResult: (Result) -> Unit) {
        if (participants.dealer.getBlackJackState() == State.Finish.BlackJack) {
            onResult(Result.DRAW)
        } else {
            onResult(Result.WIN)
        }
    }

    private fun compareToResult(
        player: Player,
        onResult: (Result) -> Unit,
    ) {
        val dealerScore = participants.dealer.getBlackJackScore()
        val playerScore = player.getBlackJackScore()
        when {
            dealerScore < playerScore -> onResult(Result.WIN)
            dealerScore == playerScore -> onResult(Result.DRAW)
            dealerScore > playerScore -> onResult(Result.LOSE)
        }
    }

    companion object {
        private const val ADD_RESULT_COUNT: Int = 1
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
