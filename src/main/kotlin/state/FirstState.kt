package state

import domain.card.Card
import domain.card.Cards

class FirstState(cards: Cards = Cards(listOf())) : RunningState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size < FIST_STATE_BOUNDARY_SIZE) { ERROR_FIRST_STATE }
    }

    override fun next(nextCards: Cards): State = when {
        nextCards.isBlackJack -> BlackJackState(nextCards)
        nextCards.size == FIST_STATE_BOUNDARY_SIZE -> HitState(nextCards)
        else -> FirstState(nextCards)
    }

    override fun stay(): State {
        throw IllegalStateException(ERROR_INSUFFICIENT_CARDS_FOR_STAY)
    }

    companion object {
        const val FIST_STATE_BOUNDARY_SIZE = 2
        private const val ERROR_FIRST_STATE = "[ERROR] 카드가 두 장 미만어야 합니다."
        private const val ERROR_INSUFFICIENT_CARDS_FOR_STAY = "[ERROR] 카드가 두 장 미만이므로 stay 할 수 없습니다."
    }
}
