package blackjack.domain.model

import blackjack.domain.model.Deck.Companion.START_CARD_COUNT

class Cards private constructor(private val _cards: MutableList<Card>) {
    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun showCards(count: Int = _cards.count()): List<Card> {
        return _cards.take(count).map { it.copy() }
    }

    fun accept(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getScore(): Int {
        val score = this._cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = this._cards.any { it.rank == Rank.ACE }

    fun isBust(): Boolean = getScore() > BUST_THRESHOLD

    fun isStartCardCount(): Boolean = _cards.count() == START_CARD_COUNT

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val MAX_BONUS_SCORE = 11
        private const val BONUS_SCORE = 10
    }
}
