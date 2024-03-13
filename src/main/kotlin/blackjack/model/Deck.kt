package blackjack.model

class Deck(cardList: List<Card>) {
    private val deck: MutableList<Card> = cardList.toMutableList()

    constructor() : this(oneDeck.shuffled())

    private var idx = 0

    private fun pick(): Card {
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
        private val oneDeck =
            Suit.entries.flatMap { suit ->
                productNumberAndSuit(suit)
            }

        private fun productNumberAndSuit(suit: Suit) = CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) }
    }
}
