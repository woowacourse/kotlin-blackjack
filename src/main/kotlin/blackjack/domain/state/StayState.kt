package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class StayState(cards: Cards, override val earningRate: Double = 1.0) : FinishedState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))
}
