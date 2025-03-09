package blackjack.domain.model

class Hand() {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    constructor(cards: List<Card>) : this() {
        add(cards)
    }

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun computeScore(): Int {
        val score = _cards.sumOf { it.rank.score }
        return score + computeBonusScore(score)
    }

    private fun computeBonusScore(score: Int): Int {
        if (score + BONUS_SCORE <= BUST_THRESHOLD && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = _cards.any { it.rank == Rank.ACE }

    fun isBusted(): Boolean {
        return computeScore() > BUST_THRESHOLD
    }

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val BONUS_SCORE = 10
    }
}
