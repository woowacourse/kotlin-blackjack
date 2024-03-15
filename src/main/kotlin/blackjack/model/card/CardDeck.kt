package blackjack.model.card

object CardDeck {
    private val cardDeck =
        CardShape.entries.flatMap { shape ->
            CardNumber.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled()

    private var cardIndex = 0

    private fun shuffleDeck(): List<Card> = cardDeck.shuffled().toList()

    fun getRandomCard(): Card {
        val result =
            runCatching {
                cardDeck[cardIndex++]
            }
        return result.getOrElse {
            resetCardDeck()
            getRandomCard()
        }
    }

    private fun resetCardDeck() {
        cardIndex = 0
        shuffleDeck()
    }
}
