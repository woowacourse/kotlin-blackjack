package blackjack.domain.person

import blackjack.domain.card.Card

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun copy(): Hand =
        Hand().apply {
            _cards.addAll(this@Hand._cards)
        }
}
