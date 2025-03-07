package model

enum class CardRank(val score: Int, val title: String) {
    ACE(11, "A"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
}
