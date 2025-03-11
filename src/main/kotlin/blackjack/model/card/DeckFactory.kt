package blackjack.model.card

object DeckFactory {
    fun createDeck(): ArrayDeque<Card> {
        val cards = generateCards().shuffled()
        return ArrayDeque<Card>().create(cards)
    }

    private fun generateCards(): MutableList<Card> =
        Shape.entries.flatMap { shape ->
            Number.entries.map { number ->
                Card(shape, number)
            }
        }.toMutableList()

    private fun <T> ArrayDeque<T>.create(elements: List<T>): ArrayDeque<T> {
        this.addAll(elements)
        return this
    }
}
