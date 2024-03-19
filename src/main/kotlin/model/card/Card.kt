package model.card

class Card private constructor(val denomination: Denomination, val suit: Suit) {
    fun isAce(): Boolean = (denomination == Denomination.ACE)

    companion object {
        fun of(
            denomination: Denomination,
            suit: Suit,
        ): Card {
            return Card(denomination, suit)
        }
    }
}
