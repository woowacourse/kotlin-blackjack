package blackjack.domain.model.participant

import blackjack.domain.model.GameResult.BLACKJACK_WIN
import blackjack.domain.model.GameResult.DRAW
import blackjack.domain.model.GameResult.LOSE
import blackjack.domain.model.GameResult.WIN

class PlayerBetInfo(
    private val player: Player,
    private val betAmount: BetAmount,
) {
    fun getProfit(dealer: Dealer): Map<Player, Double> {
        val betAmount: Double = betAmount.value
        val profit: Double =
            when (player.compareTo(dealer)) {
                BLACKJACK_WIN -> betAmount * 1.5
                WIN -> betAmount
                DRAW -> 0.0
                LOSE -> -betAmount
            }
        return mapOf(player to profit)
    }
}
