package blackjack.model.role

import blackjack.model.card.CardDeck
import blackjack.model.card.CardHand
import blackjack.model.card.state.CardHandState
import blackjack.model.card.state.Decide
import blackjack.model.card.state.Done
import blackjack.model.card.state.InitialState
import blackjack.model.config.GameRule.INITIAL_CARD_COUNT

abstract class Role(open val name: PlayerName) {
    var state: CardHandState = InitialState(CardHand())

    init {
        repeat(INITIAL_CARD_COUNT) {
            state = state.draw(CardDeck.getRandomCard())
        }
    }

    fun runPhase(
        canDraw: () -> Boolean,
        cardDrawResult: () -> Unit,
    ): CardHandState {
        while (state is Decide && canDraw.invoke()) {
            state = state.draw(CardDeck.getRandomCard())
            cardDrawResult.invoke()
            if (state is Done) return state
        }
        state = state.stay()
        return state
    }
}
