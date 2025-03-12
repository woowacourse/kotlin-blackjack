package blackjack.domain.person

import blackjack.domain.card.Card

class PersonCards {
    private val cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        cards.add(card)
    }

    fun list(): List<Card> {
        return cards.toList()
    }
}
