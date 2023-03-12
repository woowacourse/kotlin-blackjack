package state

import domain.card.Card
import domain.card.Cards

class StayState(val cards: Cards) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }
}
