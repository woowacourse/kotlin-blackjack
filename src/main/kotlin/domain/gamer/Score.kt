package domain.gamer

class Score(value: Int) {
    var value = 0
        private set

    operator fun plus(other: Score): Score = Score(value + other.value)
    operator fun minus(other: Score): Score = Score(value - other.value)
    operator fun compareTo(other: Score): Int {
        return compareValuesBy(this, other, Score::value)
    }

    companion object {
        val BLACKJACK_SCORE: Score = Score(21)
        val ACE_SUBTRACT_VALUE: Score = Score(10)
    }
}