package blackjack

object CardDeck {
    private val cardDeck =
        CardShape.entries.flatMap { shape ->
            CardNumber.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled()

    private var cardIndex = 0

    fun getRandomCard(): Card = cardDeck[cardIndex++]
}
