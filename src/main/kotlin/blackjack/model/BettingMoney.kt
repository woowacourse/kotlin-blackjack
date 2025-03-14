package blackjack.model

class BettingMoney(
    value: Int,
) : Money(value) {
    init {
        require(value in 1..300_000_000) { "베팅 금액은 최소 1원 이상 최대 3억원까지 가능합니다. $value 는 범위에 해당되지 않습니다." }
    }

    fun getProfit(): Money = Money(value - initialValue)
}
