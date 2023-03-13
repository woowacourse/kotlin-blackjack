package blackjack.domain.card

data class Card(val number: CardNumber, val shape: CardShape) {

    val value: Int = number.value

    fun isAce(): Boolean = number == CardNumber.ACE
}
