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

        private const val PLUS_SCORE_FOR_ACE = 10

        //TODO: 21이라는 상수를 왜 여기저기서 사용하게 될까?
        fun decideAceValue(currentSum: Int): Int {
            if (currentSum + PLUS_SCORE_FOR_ACE <= 21) {
                return currentSum + PLUS_SCORE_FOR_ACE
            }

            return currentSum
        }
    }
}
