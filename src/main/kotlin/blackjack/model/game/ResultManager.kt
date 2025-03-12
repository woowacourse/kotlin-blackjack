package blackjack.model.game

import blackjack.model.participant.Dealer
import blackjack.model.participant.Name
import blackjack.model.participant.Players
import blackjack.model.rule.WinningResult

class ResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun playerResults(): Map<Name, WinningResult> =
        players.value.associate { player ->
            player.name to
                WinningResult.from(player.score(), dealer.score(), player.isBust(), dealer.isBust())
        }

    fun dealerResult(): Map<WinningResult, ResultCount> {
        val resultCounts =
            players.value
                .map { player ->
                    WinningResult.from(dealer.score(), player.score(), dealer.isBust(), player.isBust())
                }.groupingBy { it }
                .eachCount()

        return WinningResult.entries.associateWith { ResultCount(resultCounts[it] ?: INITIAL_SCORE) }
    }

    companion object {
        private const val INITIAL_SCORE = 0
    }
}
