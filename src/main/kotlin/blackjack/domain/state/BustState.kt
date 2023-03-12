package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money

class BustState(cards: Cards, override val earningRate: Double = -1.0) : FinishedState(cards) {
    init {
        check(cards.isBust) { "버스트 상태는 21점을 초과해야 합니다." }
    }

    override fun profit(other: CardState, betMoney: BetMoney): Money =
        Money(betMoney.getAmount() * earningRate)
}
