package blackjack.domain.model

import java.util.Stack

class Deck {
    private val cards: Stack<Card> = Stack()

    fun draw(count: Int = DRAW_DEFAULT_COUNT): List<Card> {
        if (cards.count() < count) cards.addAll(blackJackCards.shuffled())
        return List(count.coerceAtMost(blackJackCards.count())) { cards.pop() }
    }

    companion object {
        private val blackJackCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }
        const val START_CARD_COUNT = 2
        const val DRAW_DEFAULT_COUNT = 1

        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
