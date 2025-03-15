package blackjack.domain

import blackjack.domain.card.Card

class Hand(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards : List<Card> get() = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        return _cards.sumOf { it.denomination.value }
    }
}