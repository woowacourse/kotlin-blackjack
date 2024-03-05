package blackjack.model

class Deck(count: Int = 1) {
    var deck: List<Card>

    init {
        deck = List(count) { oneDeck }.flatten().shuffled()
    }

    companion object {
        val oneDeck = Suit.entries.flatMap { suit -> CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) } }
    }
}
