package model.card

enum class Suit(val order: Int) {
    SPADE(0),
    CLOVER(1),
    HEART(2),
    DIAMOND(3),
}

fun intToSuitType(number: Int): Suit {
    return when (number / DIVIDER) {
        Suit.SPADE.order -> Suit.SPADE
        Suit.CLOVER.order -> Suit.CLOVER
        Suit.HEART.order -> Suit.HEART
        Suit.DIAMOND.order -> Suit.DIAMOND
        else -> throw IllegalArgumentException("잘못된 숫자를 입력하셨습니다. (0~51 사이의 숫자만 가능합니다)")
    }
}

private const val DIVIDER = 13
