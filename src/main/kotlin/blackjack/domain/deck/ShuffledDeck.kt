package blackjack.domain.deck

import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import java.util.ArrayDeque
import java.util.Deque

class ShuffledDeck : Deck {
    private val deckPool: MutableList<Deque<TrumpCard>> = mutableListOf()
    private var currentDeckIndex = 0

    init {
        repeat(MAX_DECK_COUNT) {
            deckPool.add(createDeck())
        }
    }

    override fun pop(): TrumpCard {
        require(deckPool.isNotEmpty()) { ERROR_EMPTY_DECK_POOL_MESSAGE }

        val currentDeck = deckPool[currentDeckIndex]

        if (currentDeck.isEmpty()) {
            if (currentDeckIndex < MAX_DECK_COUNT) {
                currentDeckIndex++
            } else {
                throw IllegalArgumentException(ERROR_EMPTY_DECK_MESSAGE)
            }
        }

        return currentDeck.pop()
    }

    override fun makeCards(): List<TrumpCard> = createDeck().toList()

    private fun createDeck(): Deque<TrumpCard> {
        val shuffledCards =
            Shape.entries
                .flatMap { shape ->
                    CardTier.entries.map { tier ->
                        TrumpCard(tier, shape)
                    }
                }.shuffled()
        return ArrayDeque(shuffledCards)
    }

    companion object {
        const val MAX_DECK_COUNT = 7

        const val ERROR_EMPTY_DECK_MESSAGE = "[ERROR] 더 이상 뽑을 카드가 없습니다."
        const val ERROR_EMPTY_DECK_POOL_MESSAGE = "[ERROR] 사용할 수 있는 덱이 없습니다."
    }
}
