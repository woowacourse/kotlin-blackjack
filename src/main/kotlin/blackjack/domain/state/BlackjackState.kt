package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.money.Money

class BlackjackState(cards: Cards, override val earningRate: Double = 1.5) : FinishedState(cards) {
    init {
        check(cards.isBlackjack) { "카드의 총 합이 21점이 아닙니다." }
    }

    override fun profit(other: CardState, money: Money): Money {
        if (other is BlackjackState) return Money(0)
        return money * earningRate
    }
}
