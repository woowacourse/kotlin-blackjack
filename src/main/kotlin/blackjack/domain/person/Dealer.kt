package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.card.Deck
import blackjack.domain.state.DealerState

class Dealer : Person() {
    init {
        setGameState(DealerState.FIRST_TURN)
    }

    fun draw(deck: Deck) {
        val amount = if (gameState == DealerState.FIRST_TURN) GameRule.FIRST_TURN_DRAW_AMOUNT else GameRule.HIT_DRAW_AMOUNT

        repeat(amount) {
            if (score() <= GameRule.DEALER_ADDITIONAL_DRAW_BASE_SCORE) hand.addCard(deck.draw())
        }
        updateGameState()
    }

    override fun updateGameState() {
        setGameState(DealerState.from(this))
    }
}
