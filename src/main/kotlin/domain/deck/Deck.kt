package domain.deck

import domain.card.Card

class Deck(private val deck: List<Card>) {
    fun giveCard() = deck.toMutableList().removeLast()
}
