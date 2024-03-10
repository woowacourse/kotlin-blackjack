package blackjack.model

object CardDeck {
    private val leftCards: MutableList<Card> =
        Shape.entries.flatMap { shape ->
            CardValue.entries.map { cardValue -> Card(shape.title, cardValue.title, cardValue.value) }
        }.shuffled().toMutableList()

    fun pick(): Card? = leftCards.removeLastOrNull()
}
