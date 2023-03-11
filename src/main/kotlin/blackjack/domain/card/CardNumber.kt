package blackjack.domain.card

enum class CardNumber(val value: Int) {

    A(1),
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

        private const val ADDITION_SCORE_FOR_ACE = 10
        private const val STANDARD_SCORE = 11
        private const val ZERO = 0

        fun decideAdditionalAceScore(score: Int): Int {
            if (score <= STANDARD_SCORE) {
                return ADDITION_SCORE_FOR_ACE
            }

            return ZERO
        }
    }
}