package blackjack.model

import blackjack.state.State

class GameResult(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val dealerResults: MutableMap<Result, Int> = Result.entries.associateWith { 0 }.toMutableMap()
    private val playerResults: MutableMap<Player, Result> = mutableMapOf()

    fun getDealerResults(): MutableMap<Result, Int> {
        return dealerResults
    }

    fun getPlayerResults(): MutableMap<Player, Result> {
        return playerResults
    }

    fun judgeBlackJackScores() {
        players.forEach { player ->
            judgeBlackJackScore(player)
        }
    }

    private fun judgeBlackJackScore(player: Player) {
        when (player.getBlackJackState()) {
            is State.Finish.Bust -> addDealerWin(player)
            is State.Finish.BlackJack -> applyBlackJackState(player)
            is State.Finish.Stay, State.Action.Hit ->
                compareToResult(player)
        }
    }

    private fun applyBlackJackState(player: Player) {
        if (dealer.getBlackJackState() == State.Finish.BlackJack) {
            addDealerDraw(player)
        } else {
            addDealerLose(player)
        }
    }

    private fun compareToResult(player: Player) {
        val dealerScore = dealer.getBlackJackScore()
        val playerScore = player.getBlackJackScore()
        when {
            dealerScore > playerScore -> addDealerWin(player)
            dealerScore == playerScore -> addDealerDraw(player)
            dealerScore < playerScore -> addDealerLose(player)
        }
    }

    private fun applyPlayerResult(
        player: Player,
        dealerResult: Result
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
    }
}
