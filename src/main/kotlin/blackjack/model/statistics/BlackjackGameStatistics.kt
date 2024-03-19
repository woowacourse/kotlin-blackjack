package blackjack.model.statistics

import blackjack.model.Dealer
import blackjack.model.Player

class BlackjackGameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val dealerStatistics = DealerStatistics(dealer, players)
    val playerStatistics = PlayerStatistics(dealer, players)
}
