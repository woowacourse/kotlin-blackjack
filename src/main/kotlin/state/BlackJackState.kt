package state

import domain.card.Card
import domain.card.Cards

class BlackJackState(cards: Cards, override val rateOfProfit: RateOfProfit = RateOfProfit.WIN_BLACKJACK_PROFIT) :
    FinishedState(cards) {
    constructor(vararg card: Card) : this(Cards(card.toList()))

    init {
        check(cards.isBlackJack) { ERROR_BLACKJACK_STATE }
    }

    override fun calculateResultProfit(other: State): RateOfProfit =
        when (other) {
            is BlackJackState -> RateOfProfit.DRAW_PROFIT
            else -> rateOfProfit
        }

    companion object {
        private const val ERROR_BLACKJACK_STATE = "[ERROR] 카드가 두 장에 합이 21이어야 합니다."
    }
}
