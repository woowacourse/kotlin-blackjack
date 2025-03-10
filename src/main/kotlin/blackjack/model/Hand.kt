package blackjack.model

class Hand(firstCard: List<Card>) {
    private val _cards: MutableList<Card> = firstCard.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = score() == BUST_SCORE

    fun score(): Int {
        val baseScore = cards.sumOf { card -> card.rank.score }
        return maxOf(baseScore.formatIfBust(), maxScoreWithAce(baseScore).formatIfBust())
    }

    fun getHandCount(): Int{
        return cards.size
    }

    private fun maxScoreWithAce(hardScore: Int): Int {
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
