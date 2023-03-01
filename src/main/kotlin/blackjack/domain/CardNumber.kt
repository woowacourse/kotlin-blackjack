package blackjack.domain

@JvmInline
value class CardNumber(private val number: Int) {
    init {
        require(number in MIN_CARD_NUMBER..MAX_CARD_NUMBER) { NUMBER_RANGE_ERROR }
    }

    fun toLetter() = when (number) {
        ACE_NUMBER -> "A"
        JACK_NUMBER -> "J"
        QUEEN_NUMBER -> "Q"
        KING_NUMBER -> "K"
        else -> number.toString()
    }

    companion object {
        private const val MIN_CARD_NUMBER = 1
        private const val MAX_CARD_NUMBER = 13
        private const val NUMBER_RANGE_ERROR = "카드 숫자의 범위는 1부터 13이어야 합니다."

        private const val ACE_NUMBER = 1
        private const val JACK_NUMBER = 11
        private const val QUEEN_NUMBER = 12
        private const val KING_NUMBER = 13
    }
}
