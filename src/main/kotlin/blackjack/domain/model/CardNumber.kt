package blackjack.domain.model

@JvmInline
value class CardNumber(
    private val cardNumber: Int,
) {
    init {
        require(cardNumber in CARD_NUMBER_RANGE) { ERROR_OUT_OF_CARD_NUMBER_RANGE }
    }

    fun getCardNumberName(): String =
        when (cardNumber) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> cardNumber.toString()
        }

    companion object {
        const val MIN_CARD_NUMBER = 1
        const val MAX_CARD_NUMBER = 13
        val CARD_NUMBER_RANGE = MIN_CARD_NUMBER..MAX_CARD_NUMBER

        private val ERROR_OUT_OF_CARD_NUMBER_RANGE = "카드 숫자는 $CARD_NUMBER_RANGE 에 속하는 값이여야 합니다"
    }
}
