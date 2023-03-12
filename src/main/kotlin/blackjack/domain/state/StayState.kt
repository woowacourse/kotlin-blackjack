package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.money.Money

class StayState(cards: Cards, override val earningRate: Double = 1.0) : FinishedState(cards) {
    override fun profit(other: CardState, money: Money): Money {
        if (other is BustState) return money
        val scoreGap = getTotalScore() - other.getTotalScore()
        return when {
            scoreGap > 0 -> money * earningRate
            scoreGap == 0 -> Money(0)
            else -> -money
        }
    }
}
