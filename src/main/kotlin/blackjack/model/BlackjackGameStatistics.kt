package blackjack.model

class BlackjackGameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val dealerStatistics = DealerStatistics(dealer, players)
    val playerStatistics = PlayerStatistics(dealer, players)
}
