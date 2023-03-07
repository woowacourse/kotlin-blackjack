package blackjack.domain

enum class CardNumber(val value: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ;

    companion object {
        fun valueOf(cardNumber: CardNumber): String {
            return when (cardNumber) {
                ACE -> "A"
                TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> cardNumber.value.toString()
                JACK -> "J"
                QUEEN -> "Q"
                KING -> "K"
            }
        }
    }
}
