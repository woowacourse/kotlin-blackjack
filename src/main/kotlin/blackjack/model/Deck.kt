package blackjack.model

class Deck {
    private var cards: MutableList<Card> = generateCards()

    fun draw(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalStateException(ERROR_NO_MORE_CARD_MESSAGE)
    }

    fun drawWithCount(count: Int): List<Card> {
        return List(count) { draw() }
    }

    private fun generateCards(): MutableList<Card> =
        Shape.entries.flatMap { shape ->
            Number.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled().toMutableList()

    companion object {
        private const val ERROR_NO_MORE_CARD_MESSAGE = "카드가 더 없습니다."
    }
}
