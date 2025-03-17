package blackjack.domain.model

@JvmInline
value class Money(private val value: Double) {
    init {
        require(value > 0) { "돈은 음수가 될 수 없습니다!" }
    }

    operator fun times(rate: Double) = value * rate
}
