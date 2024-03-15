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
                    dealerResults[result.reverse()] = dealerResults.getOrDefault(result.reverse(), 0) + ADD_RESULT_COUNT
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

    fun getPlayers(): List<Player> {
        return participants.players
    }

    fun getAlivePlayers(): List<Participant> {
        return participants.getAlivePlayers()
    }

    private fun judgeBlackJackScore(
        player: Player,
        onResult: (Result) -> Unit,
    ) {
        when (player.getGameState()) {
            is State.Finish.Bust -> onResult(Result.LOSE)
            is State.Finish.BlackJack -> checkBlackJackState(onResult)
            is State.Finish.Stay ->
                matchToResult(
                    player = player,
                    onResult = onResult,
                )

            is State.Action.Hit -> {}
        }
    }

    private fun checkBlackJackState(onResult: (Result) -> Unit) {
        if (participants.dealer.getGameState() == State.Finish.BlackJack) {
            onResult(Result.DRAW)
        } else {
            onResult(Result.WIN)
        }
    }

    private fun matchToResult(
        player: Player,
        onResult: (Result) -> Unit,
    ) {
        onResult(player.match(participants.dealer))
    }

    companion object {
        private const val ADD_RESULT_COUNT: Int = 1
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
