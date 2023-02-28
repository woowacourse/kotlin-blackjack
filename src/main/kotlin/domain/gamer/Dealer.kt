package domain.gamer

import domain.card.Card

class Dealer {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun pick(card: Card) {
        _cards.add(card)
    }
}
