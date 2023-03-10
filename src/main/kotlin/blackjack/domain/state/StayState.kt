package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class StayState(cards: Cards) : FinishedState(cards) {
    constructor(vararg cards: Card) : this(Cards(*cards))
}
