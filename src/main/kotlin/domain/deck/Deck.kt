package domain.deck

import domain.card.Card

class Deck(private val deck: List<Card>) {
    private val _deck = deck.toMutableList()

    fun giveCard() = _deck.removeLast()
}
