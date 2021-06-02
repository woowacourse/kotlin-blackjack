package domain.card

enum class NumberType(val point: Int, val specialPoint: Int = 0) {
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
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    fun myFunc() {}

}
