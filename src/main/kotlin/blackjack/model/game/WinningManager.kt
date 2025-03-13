package blackjack.model.game

import blackjack.model.participant.Dealer
import blackjack.model.participant.Name
import blackjack.model.participant.Players

class WinningManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun generateResult(): WinningResult {
        val playerResults = playerResult()
        val dealerResult = dealerResult(playerResults)

        return WinningResult(dealerResult, playerResults)
    }

    private fun playerResult(): Map<Name, WinningState> =
        players.value.associate { player ->
            player.name to
                WinningState.fromPlayer(player.score(), dealer.score(), player.handState, dealer.handState)
        }

    private fun dealerResult(playerResults: Map<Name, WinningState>): Map<WinningState, ResultCount> {
        val resultCounts =
            playerResults.values
                .groupingBy { it.reverseToDealer() }
                .eachCount()

        return WinningState.entries.associateWith { ResultCount(resultCounts[it] ?: INITIAL_SCORE) }
    }

    companion object {
        private const val INITIAL_SCORE = 0
    }
}
