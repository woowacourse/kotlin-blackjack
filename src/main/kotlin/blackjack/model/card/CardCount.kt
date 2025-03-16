package blackjack.model.card

@JvmInline
value class CardCount(
    val value: Int,
) {
    operator fun plus(other: CardCount): CardCount = CardCount(this.value + other.value)

    operator fun minus(other: CardCount): CardCount = CardCount(this.value - other.value)
}
