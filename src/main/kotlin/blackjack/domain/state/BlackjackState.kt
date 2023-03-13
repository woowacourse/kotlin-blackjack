package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money

class BlackjackState(cards: Cards) : FinishedState(cards) {
    override val earningRate: Double = 1.5

    constructor(vararg cards: Card) : this(Cards(*cards))

    init {
        check(cards.isBlackjack) { "카드의 총 합이 21점이 아닙니다." }
    }

    override fun profit(other: CardState, betMoney: BetMoney): Money {
        if (other is BlackjackState) return Money()
        return Money(betMoney.getAmount() * earningRate)
    }
}
