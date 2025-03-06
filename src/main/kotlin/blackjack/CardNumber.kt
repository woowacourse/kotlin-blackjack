package blackjack

@JvmInline
value class CardNumber(
    private val cardNumber: Int,
) {
    fun getCardNumberName(): String =
        when (cardNumber) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> cardNumber.toString()
        }
}
