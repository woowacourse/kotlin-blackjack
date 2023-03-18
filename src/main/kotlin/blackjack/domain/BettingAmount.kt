package blackjack.domain

import blackjack.domain.result.GameResult

class BettingAmount(
    val value: Int
) {

    init {
        checkValue()
    }

    fun getPayout(gameResult: GameResult): Int = (value * gameResult.ratio).toInt()

    private fun checkValue() {
        require(MINIMUM_BETTING_AMOUNT <= value) { ERROR_MESSAGE_BETTING_BOUNDARY }
        require(value <= MAXIMUM_BETTING_AMOUNT) { ERROR_MESSAGE_BETTING_BOUNDARY }
        require(value % BETTING_AMOUNT_UNIT == 0) { ERROR_MESSAGE_BETTING_UNIT }
    }

    companion object {
        private const val MINIMUM_BETTING_AMOUNT = 5_000
        private const val MAXIMUM_BETTING_AMOUNT = 500_000
        private const val BETTING_AMOUNT_UNIT = 1_000

        private const val ERROR_MESSAGE_BETTING_BOUNDARY = "최소 배팅 금액은 5,000원, 최대 배팅 금액은 500,000원 입니다."
        private const val ERROR_MESSAGE_BETTING_UNIT = "배팅 금액은 1,000원 단위로만 받을 수 있습니다."
    }
}
