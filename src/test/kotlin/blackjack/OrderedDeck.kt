package blackjack

import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.deck.Deck
import java.util.ArrayDeque
import java.util.Deque

class OrderedDeck : Deck {
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
            }
}
