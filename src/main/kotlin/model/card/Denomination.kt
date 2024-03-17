package model.card

enum class Denomination(val point: Int) {
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

fun intToValueType(number: Int): Denomination {
    return Denomination.entries[number % DIVIDER]
}

private const val DIVIDER = 13
