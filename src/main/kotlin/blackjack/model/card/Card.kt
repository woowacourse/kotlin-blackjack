package blackjack.model.card

data class Card(
    val shape: Shape,
    val title: String,
    val value: Int,
) {
    override fun toString(): String {
        return CARD_INFO.format(value, shape.title)
    }

    companion object {
        private const val LOW_ACE_VALUE = 1
        private const val HIGH_ACE_VALUE = 11
        private const val MAXIMUM_HIGH_ACE_THRESHOLD = 10
        private const val CARD_INFO = "%s%s"

        fun of(
            shape: Shape,
            cardValue: CardValue,
            total: Int,
        ): Card {
            if (cardValue.value == LOW_ACE_VALUE && total <= MAXIMUM_HIGH_ACE_THRESHOLD) {
                return Card(shape, cardValue.title, HIGH_ACE_VALUE)
            }
            return Card(shape, cardValue.title, cardValue.value)
        }
    }
}
