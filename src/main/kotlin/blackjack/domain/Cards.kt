package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class Cards {
    private val _items: MutableList<Card> by lazy { mutableListOf() }
    val items: List<Card>
        get() = _items.toList()

    fun add(card: Card) {
        _items.add(card)
    }

    fun getFirstCard(): Card = _items.first()

    fun calculateTotalScore(): Int {
        val score = _items.fold(0) { total, card -> total + card.getScore() }
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && (score + BONUS_SCORE) <= blackjackScore()) score + BONUS_SCORE else score

    private fun hasAce(): Boolean = _items.any(Card::isAce)

    companion object {
        private const val BONUS_SCORE = 10
    }
}
