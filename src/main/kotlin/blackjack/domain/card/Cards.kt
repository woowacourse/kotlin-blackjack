package blackjack.domain.card

import blackjack.domain.BlackjackGame.Companion.blackjackScore

class Cards(vararg cards: Card) {
    private val _items: MutableList<Card> by lazy { cards.toMutableList() }
    val items: List<Card>
        get() = _items.toList()

    fun add(card: Card) {
        _items.add(card)
    }

    fun isStartLimitSize(): Boolean = _items.size == START_LIMIT_SIZE

    fun getFirstCard(): Card = _items.first()

    fun calculateTotalScore(): Int {
        val score = _items.fold(0) { total, card -> total + card.getScore() }
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && (score + BONUS_SCORE) <= blackjackScore()) score + BONUS_SCORE else score

    private fun hasAce(): Boolean = _items.any(Card::isAce)

    fun isBlackjack(): Boolean = calculateAceScore(_items.take(2).sumOf { it.getScore() }) == blackjackScore()

    fun isBust(): Boolean = calculateTotalScore() > blackjackScore()

    companion object {
        private const val START_LIMIT_SIZE = 2
        private const val BONUS_SCORE = 10
    }
}
