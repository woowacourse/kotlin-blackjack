package blackjack.domain

import java.util.LinkedList

class CardPack {
    private val cards: LinkedList<Card> = LinkedList(Card.ALL_CARDS.shuffled())

    fun draw(): Card = cards.removeFirst()

    fun isEmpty() = cards.isEmpty()

    fun addCardDeck() {
        cards.addAll(LinkedList(Card.ALL_CARDS.shuffled()))
    }
}
