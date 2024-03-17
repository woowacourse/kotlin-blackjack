package model.card

enum class Suit(val mark: String, val order: Int) {
    SPADE("스페이드", 0),
    CLOVER("클로버", 1),
    HEART("하트", 2),
    DIAMOND("다이아몬드", 3),
}

fun intToMarkType(number: Int): Suit {
    return when (number / DIVIDER) {
        Suit.SPADE.order -> Suit.SPADE
        Suit.CLOVER.order -> Suit.CLOVER
        Suit.HEART.order -> Suit.HEART
        Suit.DIAMOND.order -> Suit.DIAMOND
        else -> throw IllegalArgumentException("잘못된 숫자를 입력하셨습니다. (0~51 사이의 숫자만 가능합니다)")
    }
}

fun stringToMarkType(input: String): Suit {
    return when (input.lowercase()) {
        "d" -> Suit.DIAMOND
        "s" -> Suit.SPADE
        "c" -> Suit.CLOVER
        "h" -> Suit.HEART
        else -> throw IllegalArgumentException("잘못된 MarkType 을 입력하셨습니다.")
    }
}

private const val DIVIDER = 13
