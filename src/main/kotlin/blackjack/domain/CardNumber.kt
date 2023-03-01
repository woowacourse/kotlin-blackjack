package blackjack.domain

@JvmInline
value class CardNumber(private val number: Int) {
    init {
        require(number in MIN_CARD_NUMBER..MAX_CARD_NUMBER) { NUMBER_RANGE_ERROR }
    }

    companion object {
        private const val MIN_CARD_NUMBER = 1
        private const val MAX_CARD_NUMBER = 13
        private const val NUMBER_RANGE_ERROR = "카드 숫자의 범위는 1부터 13이어야 합니다."
    }
}
