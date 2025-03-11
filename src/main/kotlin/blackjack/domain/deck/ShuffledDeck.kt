package blackjack.domain.deck

import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import java.util.ArrayDeque
import java.util.Deque

class ShuffledDeck : Deck {
    private val cards: Deque<TrumpCard> = ArrayDeque()

    init {
        cards.addAll(makeCards())
    }

    override fun pop(): TrumpCard {
        require(cards.isNotEmpty()) { ERROR_EMPTY_DECK_MESSAGE }
        return cards.pop()
    }

    override fun makeCards(): List<TrumpCard> =
        Shape.entries
            .flatMap { shape ->
                CardTier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()

    companion object {
        const val ERROR_EMPTY_DECK_MESSAGE = "[ERROR] 더 이상 뽑을 카드가 없습니다."
    }
}
