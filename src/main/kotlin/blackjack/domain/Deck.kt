package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

class Deck {
    private var _cards: ArrayDeque<Card> = create()
    val cards: List<Card> get() = _cards

    private fun create(): ArrayDeque<Card> {
        val cards =
            Suit.entries.flatMap<Suit, Card> {
                    suit ->
                Denomination.entries.map {
                        denomination ->
                    Card(suit = suit, denomination = denomination)
                }
            }.shuffled().toCollection(ArrayDeque())
        return cards
    }

    fun draw(): Card {
        if (_cards.isEmpty()) {
            _cards = create()
        }
        return _cards.removeLast()
    }
}
