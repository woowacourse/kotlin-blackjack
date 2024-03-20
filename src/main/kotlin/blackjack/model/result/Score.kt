package blackjack.model.result

@JvmInline
value class Score(val point: Int) : Comparable<Score> {
    override fun compareTo(other: Score): Int = point - other.point
}
