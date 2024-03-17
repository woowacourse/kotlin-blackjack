package blackjack.model

import java.util.LinkedList
import java.util.Queue

class ShuffleCardDeck(private val cards: Queue<Card> = LinkedList()) : CardDeck {
    init {
        generateCardDeck()
        shuffleCardDeck()
    }

    private fun generateCardDeck() {
        Denomination.entries.flatMap { denomination ->
            Suit.entries.map { suit ->
                Card(denomination, suit)
            }
        }.forEach { card ->
            cards.offer(card)
        }
    }

    override fun draw(): Card {
        if (cards.isEmpty()) throw IllegalArgumentException(EMPTY_CARD_DECK)
        return cards.poll()
    }

    private fun shuffleCardDeck() {
        val shuffledCards = cards.toList().toMutableList()
        shuffledCards.shuffle()
        cards.clear()
        cards.addAll(shuffledCards)
    }

    companion object {
        private const val EMPTY_CARD_DECK = "카드 덱에서 뽑을 수 있는 카드가 없습니다."
        const val MAX_DRAW_COUNT: Int = 52
    }
}
