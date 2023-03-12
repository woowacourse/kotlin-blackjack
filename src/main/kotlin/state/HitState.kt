package state

import domain.card.Card
import domain.card.Cards

class HitState(cards: Cards) : RunningState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(
            (cards.size > FirstState.FIST_STATE_BOUNDARY_SIZE && cards.isBust.not()) ||
                (cards.size == FirstState.FIST_STATE_BOUNDARY_SIZE) && cards.isBlackJack.not()
        ) {
            ERROR_HIT_STATE
        }
    }

    override fun next(nextCards: Cards): State = when {
        nextCards.isBust -> BustState(nextCards)
        else -> HitState(nextCards)
    }

    override fun stay(): State {
        return StayState(cards.deepCopy())
    }

    companion object {
        private const val ERROR_HIT_STATE = "[ERROR] 카드가 두 장에 21미만이거나 세장이상에 21이하여야 합니다."
    }
}
