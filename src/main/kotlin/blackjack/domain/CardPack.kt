package blackjack.domain

import java.util.LinkedList

class CardPack {
    private val _cards: LinkedList<Card> = LinkedList(Card.ALL_CARDS.shuffled())
    val cards
        get() = LinkedList(_cards)

    init {
        cards.shuffle()
    }

    fun draw(): Card {
        val card = _cards.removeFirst()

        if (cards.size == 0) {
            _cards.addAll(Card.ALL_CARDS)
            _cards.shuffle()
        }

        return card
    }
}
