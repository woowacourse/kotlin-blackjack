package domain.score

private const val BLACK_JACK_POINT = 21
private const val NUMBER_OF_INITIAL_CARDS = 2

enum class ScoreType {
    UNDER,
    BLACK_JACK,
    OVER;

    fun isUnder() = this == UNDER

    fun isBlackJack() = this == BLACK_JACK

    fun isOver() = this == OVER

    companion object {
        fun of(point: Int, numberOfCards: Int): ScoreType {
            if (point == BLACK_JACK_POINT && numberOfCards <= NUMBER_OF_INITIAL_CARDS) {
                return BLACK_JACK
            }

            if (point <= BLACK_JACK_POINT) {
                return UNDER
            }

            return OVER
        }
    }
}
