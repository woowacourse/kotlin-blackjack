package model.card

enum class ValueType(val rank: String, val amount: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
}

fun intToValueType(number: Int): ValueType {
    return ValueType.values()[number % DIVIDER]
}

fun stringToValueType(input: String): ValueType {
    return when (input.lowercase()) {
        "1" -> ValueType.ACE
        "a" -> ValueType.ACE
        "2" -> ValueType.TWO
        "3" -> ValueType.THREE
        "4" -> ValueType.FOUR
        "5" -> ValueType.FIVE
        "6" -> ValueType.SIX
        "7" -> ValueType.SEVEN
        "8" -> ValueType.EIGHT
        "9" -> ValueType.NINE
        "10" -> ValueType.TEN
        "j" -> ValueType.JACK
        "q" -> ValueType.QUEEN
        "k" -> ValueType.KING
        else -> throw IllegalArgumentException("잘못된 ValueType 을 입력하셨습니다.")
    }
}

private const val DIVIDER = 13
