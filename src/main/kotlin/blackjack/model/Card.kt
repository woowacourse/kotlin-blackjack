package blackjack.model

data class Card(
    val shape: CardShape,
    val denomination: Denomination,
) {
    fun changeDenominationToInt(): Int {
        if (denomination.title.toIntOrNull() in 2..10) {
            return denomination.title.toInt()
        }
        if (denomination.title == "Q" || denomination.title == "J" || denomination.title == "K") {
            return 10
        }
        return 0
    }

    fun containsAce(card: Card): Boolean = card.denomination.title == Denomination.ACE.title
}
