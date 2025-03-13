package blackjack.model.game

import blackjack.model.participant.Name
import blackjack.model.participant.Participants

class WinningManager(
    private val participants: Participants,
) {
    fun result(): WinningResult {
        val playerResults = playerResult()
        val dealerResult = dealerResult(playerResults)

        return WinningResult(dealerResult, playerResults)
    }

    private fun playerResult(): Map<Name, WinningState> =
        participants.players.value.associate { player ->
            player.name to
                WinningState.fromPlayer(
                    playerScore = player.score(),
                    dealerScore = participants.dealer.score(),
                    playerHandState = player.handState,
                    dealerHandState = participants.dealer.handState,
                )
        }

    private fun dealerResult(playerResults: Map<Name, WinningState>): Map<WinningState, Count> {
        val resultCounts =
            playerResults.values
                .groupingBy { it.reverseToDealer() }
                .eachCount()

        return WinningState.entries.associateWith {
            Count(resultCounts.getOrDefault(it, INITIAL_SCORE))
        }
    }

    companion object {
        private const val INITIAL_SCORE = 0
    }
}
