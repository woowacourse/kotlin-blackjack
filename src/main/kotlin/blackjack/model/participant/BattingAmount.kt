package blackjack.model.participant

class BattingAmount(val amount: Int) {
    init {
        require(amount >= MIN_BATTING_AMOUNT) { INVALID_BATTING_AMOUNT_MESSAGE }
        require(amount % BATTING_AMOUNT_UNIT == EXPECTED_REMAINDER) { INVALID_BATTING_AMOUNT_UNIT_MESSAGE }
    }

    companion object {
        private const val MIN_BATTING_AMOUNT = 10
        private const val BATTING_AMOUNT_UNIT = 10
        private const val EXPECTED_REMAINDER = 0
        private const val INVALID_BATTING_AMOUNT_MESSAGE = "배팅 금액은 10보다 커야 합니다."
        private const val INVALID_BATTING_AMOUNT_UNIT_MESSAGE = "배팅 금액은 10의 배수여야 합니다."
    }
}
