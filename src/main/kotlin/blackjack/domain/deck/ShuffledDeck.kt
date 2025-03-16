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
        while (true) {
            val currentDeck =
                deckPool.getOrNull(currentDeckIndex)
                    ?: throw IllegalArgumentException(ERROR_EMPTY_DECK_MESSAGE)

            if (currentDeck.isNotEmpty()) {
                return currentDeck.pop()
            } else {
                currentDeckIndex++
            }
        }
    }

    override fun makeCards(): Deque<TrumpCard> {
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
        const val MAX_DECK_COUNT = 8
        const val ERROR_EMPTY_DECK_MESSAGE = "[ERROR] 더 이상 뽑을 카드가 없습니다."
    }
}
