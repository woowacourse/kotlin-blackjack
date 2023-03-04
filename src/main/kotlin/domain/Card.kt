package domain

class Card(private val shape: Shape, val value: Value) {
    enum class Shape(val pattern: String) {
        HEARTS("하트"),
        DIAMONDS("다이아몬드"),
        CLUBS("클로버"),
        SPADES("스페이드"),
    }

    enum class Value(val number: Int, val cardSign: String) {
        ACE(1, "A"),
        TWO(2, "2"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        JACK(10, "J"),
        QUEEN(10, "Q"),
        KING(10, "K"),
    }

    override fun toString(): String {
        return "${value.cardSign}${shape.pattern}"
    }

    companion object {
        fun valueOf(card: Card): Int {
            return card.value.number
        }
    }
}
