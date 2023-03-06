package domain.gamer.cards

import domain.card.Card

class Cards(private var cards: List<Card>) {

    fun addCard(card: Card) {
        cards = cards.plus(card)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
