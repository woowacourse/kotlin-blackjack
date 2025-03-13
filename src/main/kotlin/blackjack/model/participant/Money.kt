package blackjack.model.participant

@JvmInline
value class Money(
    val value: Int = DEFAULT_MONEY,
) {
    override fun toString(): String = value.toString()

    companion object {
        const val DEFAULT_MONEY = 0
    }
}
