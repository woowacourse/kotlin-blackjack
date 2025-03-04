package blackjack.domain

import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
import java.util.*

class Pack {
    private val _cards: Deque<TrumpCard> = LinkedList()
    val cards: Deque<TrumpCard> get() = ArrayDeque(_cards.shuffled())

    init {
        makeCards()
    }

    private fun makeCards() {
        Shape.entries.flatMap { shape ->
            CardTier.entries.map { tier ->
                _cards.push(TrumpCard(tier, shape))
            }
        }
    }
}
