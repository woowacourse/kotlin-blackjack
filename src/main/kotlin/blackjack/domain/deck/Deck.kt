package blackjack.domain.deck

import blackjack.domain.card.Card
import java.util.LinkedList

data class Deck(val shuffledDeck: List<Card>) {
    private val deck = LinkedList(shuffledDeck)

    init {
        require(shuffledDeck.size == MAXIMUM_DECK_SIZE) { MAX_DECK_SIZE }
        require(shuffledDeck.distinct().size == MAXIMUM_DECK_SIZE) { MUST_NOT_DUPLICATED }
    }

    fun draw(): Card {
        require(deck.isNotEmpty()) { EMPTY_DECK }
        return deck.poll()
    }

    fun getSize() = deck.size

    companion object {
        const val MAXIMUM_DECK_SIZE = 52
        private const val MAX_DECK_SIZE = "덱의 사이즈는 52여야 합니다"
        private const val MUST_NOT_DUPLICATED = "카드는 중복될 수 없습니다"
        private const val EMPTY_DECK = "덱이 비어 있습니다"
    }
}
