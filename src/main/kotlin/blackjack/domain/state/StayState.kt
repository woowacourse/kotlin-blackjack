package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money

class StayState(cards: Cards, override val earningRate: Double = 1.0) : FinishedState(cards) {
    override fun profit(other: CardState, betMoney: BetMoney): Money {
        if (other is BustState) return Money(betMoney.getAmount())
        val scoreGap = getTotalScore() - other.getTotalScore()
        return when {
            scoreGap > 0 -> Money(betMoney.getAmount() * earningRate)
            scoreGap == 0 -> Money()
            else -> -Money(betMoney.getAmount())
        }
    }
}
