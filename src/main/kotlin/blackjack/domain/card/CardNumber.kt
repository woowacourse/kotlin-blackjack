package blackjack.domain.card

enum class CardNumber(val value: Int, private val otherValue: Int = value) {

    A(1, 11),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    companion object {

        private const val CURRENT_SUM_STANDARD = 11

        fun decideAceValue(currentSum: Int): Int {
            if (currentSum >= CURRENT_SUM_STANDARD) {
                return A.value
            }

            return A.otherValue
        }
    }
}
