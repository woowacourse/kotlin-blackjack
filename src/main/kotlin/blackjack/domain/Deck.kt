package blackjack.domain

import java.util.LinkedList

class Deck(shuffledDeck: List<Card>) {
    private val deck = LinkedList(shuffledDeck)

    init {
        require(shuffledDeck.size == MAXIMUM_DECK_SIZE) { "덱의 사이즈는 52여야 합니다" }
        require(shuffledDeck.distinct().size == MAXIMUM_DECK_SIZE) { "카드는 중복될 수 없습니다" }
    }

    fun draw(): Card {
        require(deck.isNotEmpty()) { "덱이 비어 있습니다" }
        return deck.poll()
    }

    fun getSize() = deck.size

    companion object {
        const val MAXIMUM_DECK_SIZE = 52
    }
}
