package blackjack.model

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int {
        val hardScore = hardScore()
        return maxOf(hardScore.formatIfBust(), softScore(hardScore).formatIfBust())
    }

    private fun hardScore(): Int = cards.sumOf { it.rank.score }

    private fun softScore(hardScore: Int): Int {
        val containsAce = cards.any { it.rank == CardRank.ACE }

        return if (containsAce) hardScore + SOFT_OFFSET_SCORE else hardScore
    }

    private fun Int.formatIfBust(): Int = if (this > BUST_CRITERIA) BUST_SCORE else this

    companion object {
        private const val BUST_SCORE = -1
        private const val BUST_CRITERIA = 21
        private const val SOFT_OFFSET_SCORE = 10

        fun isBusted(score: Int): Boolean = score == BUST_SCORE
    }
}
