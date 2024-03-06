package blackjack.model

class Card(
    private val denomination: Denomination,
    private val suit: Suit
) {

    fun getScore(): Int {
        return denomination.score
    }

    fun getSuit(): Suit {
        return suit
    }
}
