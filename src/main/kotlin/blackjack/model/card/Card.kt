package blackjack.model.card

class Card(val denomination: Denomination, val suit: Suit) {
    override fun toString(): String {
        return denomination.convertCardDenomination() + suit.convertToCardSuit()
    }
}
