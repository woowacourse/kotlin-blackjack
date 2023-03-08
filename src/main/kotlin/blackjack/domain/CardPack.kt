package blackjack.domain

import java.util.LinkedList

class CardPack {
    var cards: LinkedList<Card> = LinkedList(Card.ALL_CARDS)

    init {
        cards.shuffle()
    }

    fun draw(): Card {
        val card = cards.removeFirst()

        if (cards.size == 0) {
            cards.addAll(Card.ALL_CARDS)
            cards.shuffle()
        }

        return card
    }
}
