package domain.deck

import domain.card.Card

class Deck(private val deck: MutableList<Card>) {

    fun giveCard() = deck.removeLast()
}
