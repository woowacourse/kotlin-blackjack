package blackjack.model.card

enum class Rank(val point: Int, val bonusNumber: Int = 0) {
    ACE(1, 10),
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