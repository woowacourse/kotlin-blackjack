package blackjack

@JvmInline
value class Suit(
    private val index: Int,
) {
    init {
        require(index in SUIT_RANGE) { ERROR_OUT_OF_SUIT_RANGE }
    }

    fun getSuitName(): String =
        when (index) {
            0 -> "스페이드"
            1 -> "하트"
            2 -> "다이아몬드"
            else -> "클로버"
        }

    companion object {
        const val MIN_SUIT_NUMBER = 0
        const val MAX_SUIT_NUMBER = 3

        val SUIT_RANGE = MIN_SUIT_NUMBER..MAX_SUIT_NUMBER

        private val ERROR_OUT_OF_SUIT_RANGE = "카드 문양은 $SUIT_RANGE 에 속하는 값이여야 합니다"
    }
}
