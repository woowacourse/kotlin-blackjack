package blackjack.model

class BettingMoney(
    val value: Int,
) {
    init {
        require(
            value in MIN_BETTING_MONEY..MAX_BETTING_MONEY,
        ) { "베팅 금액은 최소 ${MIN_BETTING_MONEY}원 이상 최대 ${MAX_BETTING_MONEY}억원까지 가능합니다. $value 는 범위에 해당되지 않습니다." }
    }

    fun profit(result: GameResult): Profit = Profit(value * result.rate)

    companion object {
        private const val MIN_BETTING_MONEY = 1
        private const val MAX_BETTING_MONEY = 300_000_000
    }
}
