package blackjack.domain.state

import blackjack.domain.card.Cards

class Stay(cards: Cards) : EndTurn(cards) {
    override val ratio: Double = 2.0
}
