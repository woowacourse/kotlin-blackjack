package blackjack.model.card

class RandomDeck : CardDeck {
    private val _cardDeck: List<Card> = create()
    val cardDeck: List<Card>
        get() = _cardDeck

    private var cardIndex = 0

    private fun create(): List<Card> =
        Suit.entries.flatMap { suit ->
            Denomination.entries.map { denomination ->
                Card(suit, denomination)
            }
        }.shuffled()

    override fun draw(): Card {
        if (cardIndex == cardDeck.size) {
            cardIndex = 0
        }
        return cardDeck[cardIndex++]
    }
}
