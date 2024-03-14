package blackjack.model

@JvmInline
value class BetAmount(val amount: Int) {
    operator fun plus(other: BetAmount): BetAmount {
        return BetAmount(this.amount + other.amount)
    }

    companion object {
        fun from(value: String): BetAmount {
            require(value.toIntOrNull() != null) { "${value}은 양수가 아닙니다. 배팅 금액은 양수여야 합니다" }
            return BetAmount(value.toInt())
        }
    }
}
