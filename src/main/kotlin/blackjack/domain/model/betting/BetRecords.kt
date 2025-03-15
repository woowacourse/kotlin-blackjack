package blackjack.domain.model.betting

import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class BetRecords(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun dealerProfit(): Double {
        val playersProfit: Collection<Double> = playersProfit().values
        return -playersProfit.sum()
    }

    fun playersProfit(): Map<Player, Double> {
        val maps: List<Map<Player, Double>> = players.map { it.makeProfitRecord(dealer) }
        return maps.reduce { acc, map -> acc + map }
    }
}
