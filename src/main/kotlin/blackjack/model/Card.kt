package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun isDenominationAce(card: Card): Boolean = card.denomination == Denomination.ACE
}
