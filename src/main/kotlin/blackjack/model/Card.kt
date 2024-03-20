package blackjack.model

data class Card(
    private val denomination: Denomination,
    private val suit: Suit,
) {
    fun getCardDenomination(): Denomination {
        return denomination
    }

    fun getCardSuit(): Suit {
        return suit
    }
}
