package blackjack.domain.model

class Cards(initCards: List<Card> = deckCards.shuffled()) {
    private val cards: MutableList<Card> = initCards.toMutableList()

    fun draw(count: Int = DRAW_DEFAULT_COUNT): List<Card> {
        if (cards.count() < count) cards.addAll(deckCards.shuffled())
        return List(count.coerceAtMost(count)) { cards.removeFirst() }
    }

    companion object {
        private val deckCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }
        const val START_CARD_COUNT = 2
        const val DRAW_DEFAULT_COUNT = 1

        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
