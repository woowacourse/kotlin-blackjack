package blackjack.domain.card

enum class CardValue(val value: Int) {
    ACE(1),
    KING(10),
    QUEEN(10),
    JACK(10),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ;

    companion object {
        fun containsAce(values: List<CardValue>): Boolean = values.contains(ACE)
    }
}
