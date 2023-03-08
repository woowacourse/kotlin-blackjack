package blackjack.domain.card

data class Card(
    val number: CardNumber,
    val shape: CardShape
) {
    fun isAce(): Boolean = number == CardNumber.ACE
}
