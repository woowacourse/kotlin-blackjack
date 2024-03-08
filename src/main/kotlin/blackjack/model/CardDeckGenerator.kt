package blackjack.model

object CardDeckGenerator {
    fun generate(): CardDeck {
        return CardDeck(
            CardNumber.entries.flatMap { number ->
                generateCardsForNumber(number)
            }.shuffled(),
        )
    }

    private fun generateCardsForNumber(number: CardNumber): List<Card> = CardShape.entries.map { shape -> Card(number, shape) }
}
