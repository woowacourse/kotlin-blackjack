package blackjack.domain.model

import java.util.LinkedList

class Deck private constructor(private val cards: LinkedList<Card>) {
    constructor(cards: List<Card> = blackJackCards.shuffled()) : this(LinkedList(cards))

    fun draw(): Card {
        if (cards.isEmpty()) refillDeck()
        return cards.pop()
    }

    private fun refillDeck() {
        cards.addAll(blackJackCards.shuffled())
    }

    companion object {
        private val blackJackCards = Suit.entries.flatMap { suit -> makeSuitCards(suit) }

        private fun makeSuitCards(suit: Suit): List<Card> {
            return Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }
    }
}
