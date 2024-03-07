package blackjack.model

class Deck(count: Int = 1) {
    private val deck: MutableList<Card>
    private var idx = 0

    init {
        deck = List(count) { oneDeck }.flatten().shuffled().toMutableList()
    }

    fun pick(): Card {
        if (idx == deck.size) {
            idx = 0
            deck.shuffle()
        }
        return deck[idx++]
    }

    companion object {
        val oneDeck =
            Suit.entries.flatMap { suit ->
                productNumberAndSuit(suit)
            }

        private fun productNumberAndSuit(suit: Suit) = CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) }
    }
}
