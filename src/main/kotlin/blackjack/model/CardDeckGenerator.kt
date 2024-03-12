package blackjack.model

object CardDeckGenerator {
    private val cards =
        CardNumber.entries.flatMap { number ->
            generateCardsForNumber(number)
        }.toMutableList()

    fun generate(): CardDeck {
        cards.shuffle()
        return CardDeck(cards)
    }

    private fun generateCardsForNumber(number: CardNumber): List<Card> = CardShape.entries.map { shape -> Card(number, shape) }
}
