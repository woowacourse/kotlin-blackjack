package blackjack.model.role

import blackjack.model.card.CardHand
import blackjack.model.card.RandomCardDrawStrategy
import blackjack.model.card.state.CardHandState
import blackjack.model.card.state.Decide
import blackjack.model.card.state.Done
import blackjack.model.card.state.InitialState
import blackjack.model.config.GameRule.INITIAL_CARD_COUNT

abstract class Role(open val name: PlayerName) {
    private val cardDrawStrategy = RandomCardDrawStrategy
    var state: CardHandState = InitialState(CardHand())

    init {
        repeat(INITIAL_CARD_COUNT) {
            state = state.draw(cardDrawStrategy.drawCard())
        }
    }

    fun runPhase(
        canDraw: () -> Boolean,
        cardDrawResult: () -> Unit,
    ): CardHandState {
        drawAdditionalCards(canDraw, cardDrawResult)
        if (state is Done) {
            return state
        }
        state = state.stay()
        return state
    }

    private fun drawAdditionalCards(
        canDraw: () -> Boolean,
        cardDrawResult: () -> Unit,
    ) {
        while (state is Decide && canDraw.invoke()) {
            state = state.draw(cardDrawStrategy.drawCard())
            cardDrawResult.invoke()
        }
    }
}
