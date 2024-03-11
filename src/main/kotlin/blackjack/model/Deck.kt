package blackjack.model

class Deck(private val deck: MutableList<Card>) {
    constructor() : this(oneDeck.shuffled().toMutableList())

    private var idx = 0

    fun pick(): Card {
        if (idx == deck.size) {
            idx = 0
            deck.shuffle()
        }
        return deck[idx++]
    }
    infix fun giveCardTo(participant: Participant) {
        participant.addCard(pick())
    }

    companion object {
        val oneDeck =
            Suit.entries.flatMap { suit ->
                productNumberAndSuit(suit)
            }

        private fun productNumberAndSuit(suit: Suit) = CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) }
    }
}
