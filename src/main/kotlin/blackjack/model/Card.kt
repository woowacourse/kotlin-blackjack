package blackjack.model

data class Card(
    val shape: String,
    val title: String,
    val value: Int,
) {
    companion object {
        private const val LOW_ACE_VALUE = 1
        private const val HIGH_ACE_VALUE = 11
        private const val MAXIMUM_HIGH_ACE_THRESHOLD = 10

        fun of(
            shape: Shape,
            cardValue: CardValue,
            total: Int,
        ): Card {
            if (cardValue.value == LOW_ACE_VALUE && total <= MAXIMUM_HIGH_ACE_THRESHOLD) {
                return Card(shape.title, cardValue.title, HIGH_ACE_VALUE)
            }
            return Card(shape.title, cardValue.title, cardValue.value)
        }
    }
}
