package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val playerStatistics: Map<String, Return> by lazy {
        players.associate { player ->
            player.name to player.judge(dealer)
        }
    }

    val dealerStatistics: Map<Return, Int> by lazy {
        players.map { player ->
            dealer.judge(player)
        }.groupingBy { it }.eachCount()
    }
}
