package blackjack.model.card

object DeckFactory {
    fun createDeck(): ArrayDeque<Card> {
        val cards = CARDS.shuffled()
        return ArrayDeque<Card>().create(cards)
    }

    private val CARDS: List<Card> by lazy {
        Shape.entries
            .flatMap { shape ->
                CardNumber.entries.map { number ->
                    Card(shape, number)
                }
            }.toList()
    }

    private fun <T> ArrayDeque<T>.create(elements: List<T>): ArrayDeque<T> {
        this.addAll(elements)
        return this
    }
}
