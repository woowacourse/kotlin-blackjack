package blackjack

import java.util.*

class Deck(private val shuffledDeck: List<Card>) {
    private val deck = LinkedList(shuffledDeck)


    init {
        require(shuffledDeck.size == MAXIMUM_DECK_SIZE) { "덱의 사이즈는 52여야 합니다" }
        require(shuffledDeck.distinct().size == MAXIMUM_DECK_SIZE) { "카드는 중복될 수 없습니다" }
    }

    fun getCard() : Card {
        return deck.poll()
    }

    companion object {
        const val MAXIMUM_DECK_SIZE = 52
    }
}
