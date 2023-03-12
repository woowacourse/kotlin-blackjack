package state

import domain.card.Card
import domain.card.Cards

class BustState(cards: Cards, override val rateOfProfit: RateOfProfit = RateOfProfit.LOSE) : FinishedState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.isBust) { ERROR_BUST_STATE }
    }

    override fun resultProfit(other: State): RateOfProfit {
        otherIsFinished(other)
        return rateOfProfit
    }

    companion object {
        private const val ERROR_BUST_STATE = "[ERROR] 카드가 버스트 상태가 아닙니다."
    }
}
