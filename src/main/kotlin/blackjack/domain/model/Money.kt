package blackjack.domain.model

@JvmInline
value class Money(private val value: Double) {
    init {
        require(value > 0)
    }

    operator fun times(rate: Double) = value * rate
}
