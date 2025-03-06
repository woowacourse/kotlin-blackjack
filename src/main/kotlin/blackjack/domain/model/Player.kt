package blackjack.domain.model

open class Player(val name: String, initCards: List<Card> = listOf()) {
    private val _cards: MutableList<Card> = initCards.toMutableList()
    val cards: List<Card> get() = _cards.map { it.copy() }

    fun accept(cards: List<Card>) {
        this._cards.addAll(cards)
    }

    fun getScore(): Int {
        val score = cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce() = cards.any { it.rank == Rank.ACE }

    fun isBust(): Boolean {
        return getScore() > BUST_THRESHOLD
    }

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val MAX_BONUS_SCORE = 11
        private const val BONUS_SCORE = 10
    }
}
