package state

import domain.card.Card
import domain.card.Cards

class StayState(cards: Cards, override val rateOfProfit: RateOfProfit = RateOfProfit.WIN) : FinishedState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size >= 2 && !cards.isBust && !cards.isBlackJack) { ERROR_STAY_STATE }
    }

    override fun resultProfit(other: State): RateOfProfit {
        otherIsFinished(other)
        return when {
            other is BlackJackState -> RateOfProfit.LOSE
            other is BustState -> RateOfProfit.WIN
            this.score > other.score -> RateOfProfit.WIN
            this.score < other.score -> RateOfProfit.LOSE
            else -> RateOfProfit.DRAW
        }
    }

    companion object {
        private const val ERROR_STAY_STATE = "[ERROR] StayState 는 카드 두 장 이상에 버스트도 블랙잭도 아닌 상태입니다."
    }
}
