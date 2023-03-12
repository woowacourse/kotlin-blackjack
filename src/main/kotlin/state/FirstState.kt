package state

import domain.card.Card
import domain.card.Cards

class FirstState(cards: Cards = Cards(listOf())) : RunningState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size < 2) { ERROR_FIRST_STATE }
    }

    override fun next(nextCards: Cards): State {
        if (nextCards.size == 2) {
            if (nextCards.isBlackJack) return BlackJackState(nextCards)
            return HitState(nextCards)
        }
        return FirstState(nextCards)
    }

    override fun stay(): State {
        throw IllegalStateException(ERROR_INSUFFICIENT_CARDS_FOR_STAY)
    }

    companion object {
        private const val ERROR_FIRST_STATE = "[ERROR] 카드가 두 장 미만어야 합니다."
        private const val ERROR_INSUFFICIENT_CARDS_FOR_STAY = "[ERROR] 카드가 두 장 미만이므로 stay 할 수 없습니다."
    }
}
