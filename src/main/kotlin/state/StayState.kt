package state

import domain.card.Card
import domain.card.Cards

class StayState(cards: Cards, override val rateOfProfit: RateOfProfit = RateOfProfit.WIN_PROFIT) :
    FinishedState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size >= FirstState.FIST_STATE_BOUNDARY_SIZE && cards.isBust.not() && cards.isBlackJack.not()) {
            ERROR_STAY_STATE
        }
    }

    override fun calculateResultProfit(other: State): RateOfProfit = when {
        other is BlackJackState -> RateOfProfit.LOSE_PROFIT
        other is BustState -> rateOfProfit
        this.score > other.score -> rateOfProfit
        this.score < other.score -> RateOfProfit.LOSE_PROFIT
        else -> RateOfProfit.DRAW_PROFIT
    }

    companion object {
        private const val ERROR_STAY_STATE = "[ERROR] StayState 는 카드 두 장 이상에 버스트도 블랙잭도 아닌 상태입니다."
    }
}
