package blackjack

object Deck {
    const val INITIAL_HAND_OUT_CARD_COUNT = 2
    private val CARDS = generateCards()

    fun draw(): Card {
        return CARDS.removeFirstOrNull() ?: throw IllegalStateException("카드가 더 없습니다.")
    }

    private fun generateCards(): MutableList<Card> {
        val cards = mutableListOf<Card>()

        Shape.entries.forEach { shape ->
            Number.entries.forEach { number ->
                cards.add(Card(shape, number))
            }
        }
        return cards.shuffled().toMutableList()
    }
}
