package blackjack.model

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int {
        val hardScore = hardScore()
        return maxOf(hardScore.validate(), softScore(hardScore).validate())
    }

    private fun hardScore(): Int = cards.sumOf { it.rank.score }

    private fun softScore(score: Int): Int {
        val containsAce = cards.any { it.rank == CardRank.ACE }

        return when (containsAce) {
            true -> score + SOFT_OFFSET_SCORE
            false -> BUST_SCORE
        }
    }

    private fun Int.validate(): Int = if (this > 21) BUST_SCORE else this

    companion object {
        private const val BUST_SCORE = -1
        private const val SOFT_OFFSET_SCORE = 10

        fun isBusted(score: Int) = score == BUST_SCORE
    }
}
