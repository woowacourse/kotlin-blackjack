package blackjack.model

object CardDeckGenerator {
    private val CardDeck =
        CardDeck(
            CardNumber.entries.flatMap { number ->
                generateCardsForNumber(number)
            }.shuffled(),
        )

    fun generate(): CardDeck = CardDeck

    private fun generateCardsForNumber(number: CardNumber): List<Card> = CardShape.entries.map { shape -> Card(number, shape) }
}
