package blackjack.domain.model.participant

import blackjack.domain.model.GameResult.BLACKJACK_WIN
import blackjack.domain.model.GameResult.DRAW
import blackjack.domain.model.GameResult.LOSE
import blackjack.domain.model.GameResult.WIN

class PlayerBetInfo(
    private val player: Player,
    private val betAmount: BetAmount,
) {
    fun profit(dealer: Dealer): Map<Player, Double> {
        val betAmount: Double = betAmount.value
        val profit: Double =
            when (player.compareTo(dealer)) {
                BLACKJACK_WIN -> betAmount * BLACKJACK_RATE
                WIN -> betAmount
                DRAW -> DRAW_RATE
                LOSE -> -betAmount
            }
        return mapOf(player to profit)
    }

    companion object {
        private const val BLACKJACK_RATE = 1.5
        private const val DRAW_RATE = 0.0
    }
}
