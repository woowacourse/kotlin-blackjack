package blackjack.domain.model.card

import kotlin.runCatching

enum class Number(
    val value: List<Int>,
    val initial: String,
) {
    ACE(listOf(1, 11), "A"),
    TWO(listOf(2), "2"),
    THREE(listOf(3), "3"),
    FOUR(listOf(4), "4"),
    FIVE(listOf(5), "5"),
    SIX(listOf(6), "6"),
    SEVEN(listOf(7), "7"),
    EIGHT(listOf(8), "8"),
    NINE(listOf(9), "9"),
    TEN(listOf(10), "10"),
    JACK(listOf(10), "J"),
    QUEEN(listOf(10), "Q"),
    KING(listOf(10), "K"),
    ;

    companion object {
        fun getByOrderNumber(orderNumber: Int): Number =
            runCatching {
                entries[orderNumber - 1]
            }.getOrElse { throw IllegalArgumentException(ERROR_UNKNOWN_CARD_ORDER_NUMBER) }

        fun getByInitial(initial: String): Number =
            entries.find { it.initial == initial } ?: throw IllegalArgumentException(ERROR_UNKNOWN_CARD_INITIAL)

        const val MAX_ORDER_NUMBER = 13

        private const val ERROR_UNKNOWN_CARD_ORDER_NUMBER = "알 수 없는 카드 순서 번호입니다."
        private const val ERROR_UNKNOWN_CARD_INITIAL = "알 수 없는 카드 이니셜입니다."
    }
}
