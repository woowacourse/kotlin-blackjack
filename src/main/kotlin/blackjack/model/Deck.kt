package blackjack.model

class Deck {
    private var _cards: MutableList<Card> = generateCards()
    val cards get() = _cards.toList()

    fun draw(): Card {
        return _cards.removeFirstOrNull() ?: throw IllegalStateException(ERROR_NO_MORE_CARD_MESSAGE)
    }

    private fun generateCards(): MutableList<Card> =
        Shape.entries.flatMap { shape ->
            CardNumber.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled().toMutableList()

    companion object {
        private const val ERROR_NO_MORE_CARD_MESSAGE = "카드가 더 없습니다."
    }
}
