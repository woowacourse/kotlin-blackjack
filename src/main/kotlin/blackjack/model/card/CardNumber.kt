package blackjack.model.card

enum class CardNumber(
    val number: Int,
    val output: String = number.toString(),
) {
    ACE(1, "A"),
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
    JUMP(10),
}
