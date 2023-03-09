package blackjack.domain

data class BetAmount(val money: Int) {
    operator fun plus(dividend: BetAmount): BetAmount {
        return BetAmount(money + dividend.money)
    }

    operator fun times(dividendRate: Double): BetAmount {
        return BetAmount((money * dividendRate).toInt())
    }
}
