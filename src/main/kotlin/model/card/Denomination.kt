package model.card

enum class Denomination(val rank: String, val amount: Int) {
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

fun intToValueType(number: Int): Denomination {
    return Denomination.values()[number % DIVIDER]
}

fun stringToValueType(input: String): Denomination {
    return when (input.lowercase()) {
        "1" -> Denomination.ACE
        "a" -> Denomination.ACE
        "2" -> Denomination.TWO
        "3" -> Denomination.THREE
        "4" -> Denomination.FOUR
        "5" -> Denomination.FIVE
        "6" -> Denomination.SIX
        "7" -> Denomination.SEVEN
        "8" -> Denomination.EIGHT
        "9" -> Denomination.NINE
        "10" -> Denomination.TEN
        "j" -> Denomination.JACK
        "q" -> Denomination.QUEEN
        "k" -> Denomination.KING
        else -> throw IllegalArgumentException("잘못된 ValueType 을 입력하셨습니다.")
    }
}

private const val DIVIDER = 13
