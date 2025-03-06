package blackjack

@JvmInline
value class Suit(
    private val index: Int,
) {
    fun getSuitName(): String =
        when (index) {
            0 -> "스페이드"
            1 -> "하트"
            2 -> "다이아몬드"
            else -> "클로버"
        }
}
