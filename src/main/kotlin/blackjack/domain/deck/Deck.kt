package blackjack.domain.deck

import blackjack.domain.card.TrumpCard
import java.util.Deque

interface Deck {
    fun pop(): TrumpCard

    fun makeCards(): Deque<TrumpCard>
}
