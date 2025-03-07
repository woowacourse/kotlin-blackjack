package blackjack.model

object Deck {
    const val INITIAL_HAND_OUT_CARD_COUNT = 2
    private val CARDS = generateCards()

    fun draw(): Card {
        return CARDS.removeFirstOrNull() ?: throw IllegalStateException("카드가 더 없습니다.")
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

}
