package blackjack.domain

import java.util.LinkedList

class Deck(shuffledDeck: List<Card>) {
    private val deck = LinkedList(shuffledDeck)

    fun draw(): Card {
        require(deck.isNotEmpty()) { "덱이 비어 있습니다" }
        return deck.poll()
    }

    fun getSize() = deck.size

    fun contains(card: Card): Boolean = deck.contains(card)
}
