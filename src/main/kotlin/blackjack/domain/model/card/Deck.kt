package blackjack.domain.model.card

import blackjack.domain.model.participant.Participant

class Deck(cards: List<Card> = PACK.shuffled()) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun draw(count: Int = Participant.DEFAULT_DRAW_COUNT): List<Card> {
        if (cards.count() < count) cards.addAll(PACK.shuffled())
        return List(count.coerceAtMost(count)) { cards.removeFirst() }
    }

    companion object {
        private val PACK: List<Card> = Suit.entries.flatMap { suit -> makeCardsFrom(suit) }

        private fun makeCardsFrom(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
