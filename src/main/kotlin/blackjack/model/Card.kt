package blackjack.model

data class Card(
    val shape: String,
    val title: String,
    val value: Int,
) {
    companion object {
        fun of(
            shape: Shape,
            cardValue: CardValue,
            total: Int,
        ): Card {
            return if (cardValue.value == 1 && total <= 10) {
                Card(shape.title, cardValue.title, 11)
            } else {
                Card(shape.title, cardValue.title, cardValue.value)
            }
        }
    }
}
