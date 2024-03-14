package blackjack.model.participant

class BattingAmount(val amount: Int) {
    init {
        require(amount >= MIN_BATTING_AMOUNT) { invalidBattingAmountMessage(amount) }
        require(amount % BATTING_AMOUNT_UNIT == EXPECTED_REMAINDER) { invalidBattingAmountUnitMessage(amount) }
    }

    companion object {
        private const val MIN_BATTING_AMOUNT = 10
        private const val BATTING_AMOUNT_UNIT = 10
        private const val EXPECTED_REMAINDER = 0

        private fun invalidBattingAmountMessage(amount: Int) = "배팅 금액은 10보다 커야 합니다. 현재 입력 값: $amount"

        private fun invalidBattingAmountUnitMessage(amount: Int) = "배팅 금액은 10의 배수여야 합니다. 현재 입력 값: $amount"
    }
}
