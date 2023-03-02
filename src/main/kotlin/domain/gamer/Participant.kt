package domain.gamer

import domain.card.Card

abstract class Participant(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()
    open fun pick(card: Card) {
        _cards.add(card)
    }
}
