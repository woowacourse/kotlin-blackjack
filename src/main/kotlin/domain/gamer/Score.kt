package domain.gamer

class Score(val value: Int) : Comparable<Score> {

    operator fun plus(other: Score): Score = Score(value + other.value)
    operator fun minus(other: Score): Score = Score(value - other.value)

    override fun compareTo(other: Score): Int {
        return this.value - other.value
    }

    companion object {
        val BLACKJACK_SCORE: Score = Score(21)
        val ACE_SUBTRACT_VALUE: Score = Score(10)
    }
}