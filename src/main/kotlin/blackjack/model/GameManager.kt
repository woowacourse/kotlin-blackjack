package blackjack.model

import blackjack.state.State

class GameManager(
    private val participants: Participants,
) {
    private val cardDeck = CardDeck()
    private val dealerResults: MutableMap<Result, Int> = Result.entries.associateWith { 0 }.toMutableMap()
    private val playerResults: MutableMap<Player, Result> = mutableMapOf()

    init {
        cardDeck.cardShuffle()
    }

    fun getDealerResults(): MutableMap<Result, Int> {
        return dealerResults
    }

    fun getPlayerResults(): MutableMap<Player, Result> {
        return playerResults
    }

    fun judgeBlackJackScores() {
        participants.getPlayers().forEach { player ->
            judgeBlackJackScore(player)
        }
    }

    fun setGame() {
        repeat(INIT_HAND_CARD_COUNT) {
            getParticipants().forEach { participant ->
                participant.draw(cardDeck.draw())
            }
        }
    }

    fun applyUserDrawDecision(participant: Participant) {
        participant.draw(cardDeck.draw())
    }

    fun getParticipants(): List<Participant> {
        return participants.getPlayers() + participants.getDealer()
    }

    fun getDealer(): Dealer {
        return participants.getDealer()
    }

    fun getAlivePlayers(): List<Participant> {
        return participants.getAlivePlayers()
    }

    private fun judgeBlackJackScore(player: Player) {
        when (player.getBlackJackState()) {
            is State.Finish.Bust -> addDealerWin(player)
            is State.Finish.BlackJack -> checkBlackJackState(player)
            is State.Finish.Stay, State.Action.Hit ->
                compareToResult(player)
        }
    }

    private fun checkBlackJackState(player: Player) {
        if (participants.getDealer().getBlackJackState() == State.Finish.BlackJack) {
            addDealerDraw(player)
        } else {
            addDealerLose(player)
        }
    }

    private fun compareToResult(player: Player) {
        val dealerScore = participants.getDealer().getBlackJackScore()
        val playerScore = player.getBlackJackScore()
        when {
            dealerScore > playerScore -> addDealerWin(player)
            dealerScore == playerScore -> addDealerDraw(player)
            dealerScore < playerScore -> addDealerLose(player)
        }
    }

    private fun applyPlayerResult(
        player: Player,
        dealerResult: Result,
    ) {
        when (dealerResult) {
            Result.WIN -> playerResults[player] = Result.LOSE
            Result.DRAW -> playerResults[player] = Result.DRAW
            Result.LOSE -> playerResults[player] = Result.WIN
        }
    }

    private fun applyDealerResult(dealerResult: Result) {
        dealerResults[dealerResult] = dealerResults.getOrDefault(dealerResult, DEFAULT_COUNT) + ADD_RESULT_COUNT
    }

    private fun addDealerWin(player: Player) {
        val dealerResult = Result.WIN
        applyDealerResult(dealerResult)
        applyPlayerResult(player, dealerResult)
    }

    private fun addDealerDraw(player: Player) {
        val dealerResult = Result.DRAW
        applyDealerResult(dealerResult)
        applyPlayerResult(player, dealerResult)
    }

    private fun addDealerLose(player: Player) {
        val dealerResult = Result.LOSE
        applyDealerResult(dealerResult)
        applyPlayerResult(player, dealerResult)
    }

    companion object {
        private const val DEFAULT_COUNT: Int = 1
        private const val ADD_RESULT_COUNT: Int = 1
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
