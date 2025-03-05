package blackjack.domain

import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
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
                CardTier.entries.map { tier ->
                    TrumpCard(tier, shape)
                }
            }.shuffled()
}
