package blackjack.domain.deck

import blackjack.domain.TrumpCard

interface Deck {
    fun pop(): TrumpCard

    fun makeCards(): List<TrumpCard>
}
