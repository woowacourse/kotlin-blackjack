package state

import domain.card.Card
import domain.card.Cards

class StayState(cards: Cards, override val rateOfProfit: Double = 1.0) : FinishedState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.size >= 2 && !cards.isBust && !cards.isBlackJack) { ERROR_STAY_STATE }
    }

    override fun resultProfit(other: State): Double {
        otherIsFinished(other)
        return when {
            other is BlackJackState -> -rateOfProfit
            other is BustState -> rateOfProfit
            this.score > other.score -> rateOfProfit
            this.score < other.score -> -rateOfProfit
            else -> 0.0
        }
    }

    companion object {
        private const val ERROR_STAY_STATE = "[ERROR] StayState는 카드 두 장 이상에 버스트도 블랙잭도 아닌 상태입니다."
    }
}
