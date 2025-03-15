package blackjack.domain.model.betting

import blackjack.domain.model.GameResult
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class BetRecord(
    private val player: Player,
    private val betAmount: BetAmount,
) {
    fun profit(dealer: Dealer): Map<Player, Double> {
        val gameResult: GameResult = player.compareTo(dealer)
        val profit: Double = betAmount.toProfit(gameResult)
        return mapOf(player to profit)
    }
}
