package blackjack.model

class DealerStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    private val dealerStatistics: Map<GameResult, Int> by lazy {
        players.map { player ->
            dealer versus player
        }.groupingBy { it }.eachCount()
    }

    fun getWinCount(): Int = dealerStatistics[GameResult.Win] ?: 0

    fun getLoseCount(): Int = dealerStatistics[GameResult.Lose] ?: 0

    fun getDrawCount(): Int = dealerStatistics[GameResult.Draw] ?: 0
}
