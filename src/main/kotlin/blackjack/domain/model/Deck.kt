package blackjack.domain.model

class Deck(cards: List<Card> = pack.shuffled()) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun draw(count: Int = Participant.DEFAULT_DRAW_COUNT): List<Card> {
        if (cards.count() < count) cards.addAll(cards.shuffled())
        return List(count.coerceAtMost(count)) { cards.removeFirst() }
    }

    companion object {
        private val pack = Suit.entries.flatMap { suit -> makeCardsFrom(suit) }

        private fun makeCardsFrom(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
