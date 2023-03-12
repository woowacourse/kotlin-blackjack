package state

import domain.card.Card
import domain.card.Cards

class BustState(val cards: Cards) : State {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.isBust) { ERROR_BUST_STATE }
    }

    override fun draw(card: Card): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    companion object {
        private const val ERROR_BUST_STATE = "[ERROR] 카드가 버스트 상태가 아닙니다."
    }
}
