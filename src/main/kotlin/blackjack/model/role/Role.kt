package blackjack.model.role

import blackjack.model.PlayerName
import blackjack.model.card.CardDeck
import blackjack.model.card.CardHand
import blackjack.model.card.state.CardsState
import blackjack.model.card.state.Decide
import blackjack.model.card.state.Done
import blackjack.model.card.state.InitialState

abstract class Role(open val name: PlayerName) {
    var state: CardsState = InitialState(CardHand()).draw(CardDeck.getRandomCard())

    fun runPhase(canDraw: () -> Boolean): CardsState {
        while (state is Decide && canDraw.invoke()) {
            state = state.draw(CardDeck.getRandomCard())
            if (state is Done) return state
        }
        state = state.stay()
        return state
    }
}
