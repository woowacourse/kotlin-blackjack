package blackjack.domain.result

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants

class GameResult(val dealer: Dealer, val participants: Participants) {

    val dealerProfit = PlayerProfit(dealer, -getParticipantsProfit().sumOf { it.profit })

    fun getParticipantsProfit(): List<PlayerProfit> {
        return participants.values.map {
            val profit: Int = (it.calculateEarningRate(dealer).rate * it.betAmount).toInt()
            PlayerProfit(it, profit)
        }
    }
}
