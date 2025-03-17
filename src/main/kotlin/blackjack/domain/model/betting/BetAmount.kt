package blackjack.domain.model.betting

import blackjack.domain.model.GameResult
import blackjack.domain.model.GameResult.BLACKJACK_WIN
import blackjack.domain.model.GameResult.DRAW
import blackjack.domain.model.GameResult.LOSE
import blackjack.domain.model.GameResult.WIN

@JvmInline
value class BetAmount(
    private val value: Double,
) {
    init {
        require(value >= 0) { ERROR_SHOULD_BE_MORE_THAN_ZERO }
    }

    fun toProfit(gameResult: GameResult): Double =
        when (gameResult) {
            BLACKJACK_WIN -> value * BLACKJACK_RATE
            WIN -> value
            DRAW -> DRAW_RATE
            LOSE -> -value
        }

    companion object {
        private const val BLACKJACK_RATE = 1.5
        private const val DRAW_RATE = 0.0
        const val ERROR_SHOULD_BE_MORE_THAN_ZERO = "베팅 금액은 0원 이상이어야 합니다."
    }
}
