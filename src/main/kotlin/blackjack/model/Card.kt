package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun isAce(): Boolean = denomination == Denomination.ACE
}
