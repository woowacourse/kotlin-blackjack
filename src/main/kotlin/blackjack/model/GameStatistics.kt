package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val playerStatistics: Map<String, GameResult> by lazy {
        players.associate { player ->
            player.name to (player versus dealer)
        }
    }
    val dealerStatistics: Map<GameResult, Int> by lazy {
        players.map { player ->
            dealer versus player
        }.groupingBy { it }.eachCount()
    }
}
