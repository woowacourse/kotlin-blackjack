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
            deckPool.add(makeCards())
        }
    }

    override fun pop(): TrumpCard {
        val currentDeck =
            deckPool.getOrNull(currentDeckIndex)
                ?: throw IllegalArgumentException(ERROR_EMPTY_DECK_MESSAGE)

        return if (currentDeck.isNotEmpty()) {
            currentDeck.pop()
        } else {
            currentDeckIndex++
            pop()
        }
    }

    override fun makeCards(): Deque<TrumpCard> = ArrayDeque(SHUFFLED_DECK)

    companion object {
        private const val MAX_DECK_COUNT = 8
        private const val ERROR_EMPTY_DECK_MESSAGE = "[ERROR] 더 이상 뽑을 카드가 없습니다."

        private val SHUFFLED_DECK: List<TrumpCard> by lazy {
            Shape.entries
                .flatMap { shape ->
                    CardTier.entries.map { tier ->
                        TrumpCard(tier, shape)
                    }
                }.shuffled()
        }
    }
}
