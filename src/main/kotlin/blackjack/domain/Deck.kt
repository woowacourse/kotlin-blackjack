package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

class Deck private constructor(private val _cards: ArrayDeque<Card>) {
    val cards: List<Card> get() = _cards

    companion object {
        fun createShuffled(): Deck {
            return Deck(generateShuffledCards())
        }

        fun createCustomDeck(customCards: ArrayDeque<Card>): Deck {
            return Deck(customCards)
        }

        private fun generateShuffledCards(): ArrayDeque<Card> {
            return Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination ->
                    Card(suit, denomination)
                }
            }.shuffled().toCollection(ArrayDeque())
        }
    }

    fun draw(): Card {
        if (_cards.isEmpty()) {
            refillDeck()
        }
        return _cards.removeLast()
    }

    private fun refillDeck() {
        _cards.addAll(generateShuffledCards())
    }
}
