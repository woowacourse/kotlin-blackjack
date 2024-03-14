package blackjack.model

object CardDeckGenerator {
    private val totalCards =
        CardNumber.entries.flatMap { number ->
            generateCardsForNumber(number)
        }.toMutableList()

    fun generate(): CardDeck {
        totalCards.shuffle()
        return CardDeck(
            totalCards,
        )
    }

    private fun generateCardsForNumber(number: CardNumber): List<Card> {
        return CardShape.entries.map { shape ->
            Card(number, shape)
        }
    }
}
