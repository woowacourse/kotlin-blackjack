package blackjack.domain

data class Card(
    val number: CardNumber,
    val shape: CardShape
) {
    val value: Int = number.value
}
