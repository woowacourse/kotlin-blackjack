package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val playerStatistics: Map<String, Long> by lazy {
        players.associate { player ->
            player.name to player.calculateBetAmount(dealer)
        }
    }

    val dealerStatistics: Long by lazy {
        -playerStatistics.values.sum()
    }
}
