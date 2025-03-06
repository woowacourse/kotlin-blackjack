package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun containsAce(card: Card): Boolean = card.denomination.title == Denomination.ACE.title
}
