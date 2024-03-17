package blackjack.model.statistics

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player

class DealerStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    private val dealerStatistics: Map<GameResult, Int> by lazy {
        players.map { player ->
            dealer versus player
        }.groupingBy { it }.eachCount()
    }

    fun getWinCount(): Int = dealerStatistics.getOrDefault(GameResult.Win, 0)

    fun getLoseCount(): Int = dealerStatistics.getOrDefault(GameResult.Lose, 0)

    fun getDrawCount(): Int = dealerStatistics.getOrDefault(GameResult.Draw, 0)
}
