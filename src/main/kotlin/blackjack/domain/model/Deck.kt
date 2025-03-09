package blackjack.domain.model

class Deck(cards: List<Card> = cardPack.shuffled()) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun draw(count: Int = DEFAULT_DRAW_COUNT): List<Card> {
        if (cards.count() < count) cards.addAll(cards.shuffled())
        return List(count.coerceAtMost(count)) { cards.removeFirst() }
    }

    companion object {
        private val cardPack = Suit.entries.flatMap { suit -> makeCards(suit) }
        const val INITIAL_DRAW_COUNT = 2
        const val DEFAULT_DRAW_COUNT = 1

        private fun makeCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
