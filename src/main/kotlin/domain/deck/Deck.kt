package domain.deck

import domain.card.Card

class Deck(private var deck: List<Card>) {
    fun giveCard(): Card {
        val result = deck.last()
        deck = deck.minus(result)
        return result
    }
}
