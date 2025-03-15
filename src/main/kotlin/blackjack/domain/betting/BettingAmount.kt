package blackjack.domain.betting

@JvmInline
value class BettingAmount(val value: Int) {
    init {
        require(value > 0) {
            "[ERROR] 배팅 금액이 올바르지 않습니다: $value"
        }
    }
}
