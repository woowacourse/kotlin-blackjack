package domain

class Card(val shape: Shape, val value: Value) {
    enum class Shape {
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES,
    }

    enum class Value(val number: Int) {
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
    }

    companion object {
        fun valueOf(card: Card): Int {
            return card.value.number
        }
    }
}
