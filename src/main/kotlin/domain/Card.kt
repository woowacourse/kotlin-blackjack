package domain

class Card(val shape: Shape, val value: Value) {
    enum class Shape {
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES,
    }

    enum class Value {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
    }
}
