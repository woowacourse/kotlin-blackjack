package blackjack.model

@JvmInline
value class BattingMoney private constructor(val amount: Double) {
    operator fun plus(other: BattingMoney): BattingMoney = BattingMoney(this.amount + other.amount)

    operator fun minus(other: BattingMoney): BattingMoney = BattingMoney(this.amount - other.amount)

    operator fun unaryMinus(): BattingMoney = BattingMoney(-this.amount)

    operator fun times(times: Double): BattingMoney = BattingMoney(this.amount * times)

    operator fun times(times: Int): BattingMoney = BattingMoney(this.amount * times)

    companion object {
        fun ofAmount(amount: Double): BattingMoney {
            require(amount > 0.0) {
                "최초 배팅 금액은 양수여야 합니다."
            }
            return BattingMoney(amount)
        }

        fun ofAmount(amount: Int): BattingMoney {
            require(amount > 0) {
                "최초 배팅 금액은 양수여야 합니다."
            }
            return BattingMoney(amount.toDouble())
        }
    }
}
