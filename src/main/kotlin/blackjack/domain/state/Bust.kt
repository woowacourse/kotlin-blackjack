package blackjack.domain.state

import blackjack.domain.card.Cards

class Bust(cards: Cards) : EndTurn(cards) {
    override val ratio: Double = 0.0
}
