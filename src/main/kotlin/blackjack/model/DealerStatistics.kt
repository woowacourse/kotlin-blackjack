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

    fun getWinCount(): Int = dealerStatistics[GameResult.승] ?: 0
    fun getLoseCount(): Int = dealerStatistics[GameResult.패] ?: 0
    fun getDrawCount(): Int = dealerStatistics[GameResult.무] ?: 0
}
