package blackjack.model

enum class CardNumber(val value: Int) {
    ACE(11),
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
    KING(10);
}

fun CardNumber.display(): String {
    return when (this) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }
}
