package blackjack.model.card

class CardDeck {
    var cardDeck = listOf<Card>()

    init {
        cardDeck = create()
    }

    private fun create(): List<Card> =
        CardShape.entries.flatMap { shape ->
            CardNumber.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled()
}
