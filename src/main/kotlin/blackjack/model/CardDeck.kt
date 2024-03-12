package blackjack.model

object CardDeck {
    private val leftCards: MutableList<Card> by lazy {
        Shape.entries.flatMap { shape ->
            CardValue.entries.map { cardValue -> Card(shape, cardValue.title, cardValue.value) }
        }.shuffled().toMutableList()
    }

    fun pick(): Card? = leftCards.removeLastOrNull()
}
