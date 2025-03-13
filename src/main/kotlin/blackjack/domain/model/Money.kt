package blackjack.domain.model

@JvmInline
value class Money(val value: Double) {
    init {
        require(value > 0)
    }
}
