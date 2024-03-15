package model.card

enum class MarkType(val mark: String, val order: Int) {
    SPADE("스페이드", 0),
    CLOVER("클로버", 1),
    HEART("하트", 2),
    DIAMOND("다이아몬드", 3),
}

fun intToMarkType(number: Int): MarkType {
    return when (number / DIVIDER) {
        MarkType.SPADE.order -> MarkType.SPADE
        MarkType.CLOVER.order -> MarkType.CLOVER
        MarkType.HEART.order -> MarkType.HEART
        MarkType.DIAMOND.order -> MarkType.DIAMOND
        else -> throw IllegalArgumentException("잘못된 숫자를 입력하셨습니다. (0~51 사이의 숫자만 가능합니다)")
    }
}

fun stringToMarkType(input: String): MarkType {
    return when (input.lowercase()) {
        "d" -> MarkType.DIAMOND
        "s" -> MarkType.SPADE
        "c" -> MarkType.CLOVER
        "h" -> MarkType.HEART
        else -> throw IllegalArgumentException("잘못된 MarkType 을 입력하셨습니다.")
    }
}

private const val DIVIDER = 13
