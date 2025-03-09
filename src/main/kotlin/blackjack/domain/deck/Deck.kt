package blackjack.domain.deck

import blackjack.domain.card.TrumpCard

interface Deck {
    fun pop(): TrumpCard

    fun makeCards(): List<TrumpCard>
}
