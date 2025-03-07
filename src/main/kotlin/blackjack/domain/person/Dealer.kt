package blackjack.domain.person

import blackjack.domain.card.Deck
import blackjack.domain.state.DealerState

class Dealer(hand: Hand) : Person(hand) {
    init {
        gameState = DealerState.FIRST_TURN
    }

    constructor() : this(hand = Hand())

    override fun draw(deck: Deck) {
        val amount = getDrawAmount(DealerState.FIRST_TURN)
        repeat(amount) {
            hand.addCard(deck.draw())
        }
        gameState = DealerState.from(this)
    }
}
