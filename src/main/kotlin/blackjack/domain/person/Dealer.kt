package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.card.Deck
import blackjack.domain.state.DealerState

class Dealer(hand: Hand) : Person(hand.copy()) {
    init {
        gameState = DealerState.FIRST_TURN
    }

    constructor() : this(hand = Hand())

    fun draw(deck: Deck) {
        val amount = if (gameState == DealerState.FIRST_TURN) GameRule.FIRST_TURN_DRAW_AMOUNT else GameRule.HIT_DRAW_AMOUNT

        repeat(amount) {
            hand.addCard(deck.draw())
        }
        gameState = DealerState.from(this)
    }
}
