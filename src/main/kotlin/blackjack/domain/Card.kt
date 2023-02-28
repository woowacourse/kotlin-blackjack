package blackjack.domain

data class Card(private val number: CardNumber, val shape: CardShape) {

    val value: Int = number.value
}
