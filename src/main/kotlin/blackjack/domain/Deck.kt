package blackjack.domain

import blackjack.domain.card.Tier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import java.util.ArrayDeque
import java.util.Deque

class Deck {
    private val cards: Deque<TrumpCard> = ArrayDeque()

    init {
        getAllCards()
    }

    fun pop(): TrumpCard = cards.pop()

    private fun getAllCards() {
        cards.addAll(makeCards())
    }

    private fun makeCards(): List<TrumpCard> =
        Shape.entries
            .flatMap { shape ->
                Tier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
}
