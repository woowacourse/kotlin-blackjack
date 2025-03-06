package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun isDenominationAce(card: Card): Boolean = card.denomination == Denomination.ACE

    fun combine(): String = denomination.title + shape.koreanName
}
