package blackjackgame.model.card

val CARDS: List<Card> = Denomination.values()
    .flatMap { card ->
        Suit.values().map { suit -> Card(suit, card) }
    }

data class Card(val suit: Suit, val denomination: Denomination) {

}
