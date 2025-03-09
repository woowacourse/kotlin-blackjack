package blackjack.domain.deck

import blackjack.domain.CardTier
import blackjack.domain.Shape
import blackjack.domain.TrumpCard
import java.util.ArrayDeque
import java.util.Deque

class ShuffledDeck : Deck {
    private val cards: Deque<TrumpCard> = ArrayDeque()

    init {
        cards.addAll(makeCards())
    }

    override fun pop(): TrumpCard = cards.pop()

    override fun makeCards(): List<TrumpCard> =
        Shape.entries
            .flatMap { shape ->
                CardTier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
}
