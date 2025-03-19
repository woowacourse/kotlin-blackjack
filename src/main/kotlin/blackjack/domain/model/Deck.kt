package blackjack.domain.model

import java.util.LinkedList

class Deck private constructor(private val cards: LinkedList<Card>) {
    fun draw(): Card? {
        return cards.removeLastOrNull()
    }

    companion object {
        fun from(cards: List<Card> = blackJackCards.shuffled()) = Deck(cards = LinkedList(cards))

        private val blackJackCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }

        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
