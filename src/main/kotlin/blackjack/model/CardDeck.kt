package blackjack.model

import java.util.LinkedList
import java.util.Queue

class CardDeck {
    private val cards: Queue<Card> = LinkedList()

    init {
        cards.addAll(
            Denomination.entries.flatMap { denomination ->
                Suit.entries.map { suit ->
                    Card(
                        denomination = denomination,
                        suit = suit,
                    )
                }
            },
        )
    }

    fun cardShuffle() {
        val shuffledCards = cards.shuffled()
        cards.clear()
        cards.addAll(shuffledCards)
    }

    fun draw(): Card {
        if (cards.isEmpty()) throw IllegalArgumentException(EMPTY_CARD_DECK)
        return cards.poll()
    }

    companion object {
        private const val EMPTY_CARD_DECK = "카드 덱에서 뽑을 수 있는 카드가 없습니다."
        const val MAX_DRAW_COUNT: Int = 52
    }
}
