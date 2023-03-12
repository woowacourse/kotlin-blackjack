package state

import domain.card.Card
import domain.card.Cards

class StayState(val cards: Cards) : State {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size >= 2 && !cards.isBust && !cards.isBlackJack) { ERROR_STAY_STATE }
    }

    override fun draw(card: Card): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(BlackJackState.ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    companion object {
        private const val ERROR_STAY_STATE = "[ERROR] StayState는 카드 두 장 이상에 버스트도 블랙잭도 아닌 상태입니다."
    }
}
