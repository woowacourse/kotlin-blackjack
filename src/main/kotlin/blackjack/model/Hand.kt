package blackjack.model

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun isBust(): Boolean = score() == BUST_SCORE

    fun score(): Int {
        val hardScore = cards.sumOf { card -> card.rank.score }
        return maxOf(hardScore.formatIfBust(), softScore(hardScore).formatIfBust())
    }

    private fun softScore(hardScore: Int): Int {
        val containsAce = cards.any { card -> card.rank == CardRank.ACE }
        return if (containsAce) hardScore + SOFT_OFFSET_SCORE else hardScore
    }

    private fun Int.formatIfBust(): Int = if (this > BUST_CRITERIA) BUST_SCORE else this

    companion object {
        private const val BUST_SCORE = -1
        private const val BUST_CRITERIA = 21
        private const val SOFT_OFFSET_SCORE = 10
    }
}
