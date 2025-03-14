package blackjack.domain.model.betting

import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class BetRecords(
    private val dealer: Dealer,
    private val records: List<BetRecord>,
) {
    fun dealerProfit(): Double {
        val playersProfit: Collection<Double> = playersProfit().values
        return -playersProfit.sum()
    }

    fun playersProfit(): Map<Player, Double> {
        val maps: List<Map<Player, Double>> = records.map { it.profit(dealer) }
        return maps.reduce { acc, map -> acc + map }
    }
}
