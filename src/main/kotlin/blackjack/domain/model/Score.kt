package blackjack.domain.model

class Score(private val cards: List<Card>) {
    val value get() = cardsScore() + getBonusScore()

    fun isBustScore() = value > MAX_SCORE

    fun isMaxScore() = value == MAX_SCORE

    operator fun compareTo(other: Score): Int {
        return this.value - other.value
    }

    override fun toString(): String = value.toString()

    private fun cardsScore() = cards.sumOf { it.rank.score }

    private fun getBonusScore(): Int {
        val totalScore = cards.sumOf { it.rank.score } + BONUS_SCORE
        if (totalScore <= MAX_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = this.cards.any { it.rank == Rank.ACE }

    private companion object {
        const val MAX_SCORE = 21
        const val BONUS_SCORE = 10
    }
}
