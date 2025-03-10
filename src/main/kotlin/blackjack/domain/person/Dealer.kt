package blackjack.domain.person

import blackjack.domain.card.Deck
import blackjack.domain.state.DealerState

class Dealer(hand: Hand) : Person(hand) {
    init {
        state = DealerState.HIT
    }

    constructor() : this(hand = Hand())

    override fun draw(deck: Deck) {
        super.draw(deck)
        state = DealerState.from(this)
    }
}
