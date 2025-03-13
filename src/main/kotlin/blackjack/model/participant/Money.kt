package blackjack.model.participant

@JvmInline
value class Money(
    val value: Int = INITIAL_MONEY,
) {
    companion object {
        const val INITIAL_MONEY = 0
    }

    override fun toString(): String = value.toString()
}
