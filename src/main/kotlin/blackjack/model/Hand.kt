package blackjack.model

import blackjack.model.card.Card

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()
    val score
        get() = 0

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
