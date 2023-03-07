package domain

import domain.card.Card
import domain.card.CardNumber

abstract class Player(
    val name: String,
    private val deck: Deck,
) {

    val cards: List<Card> get() = deck.cards.toList()

    fun addScoreTenIfHasAce(): Int {
        if ((calculateCardValueSum() < BLACK_JACK_LESS_TEN) and (countAce() != NO_ACE)) {
            return calculateCardValueSum() + ACE_CARD_PLUS_TEN
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = deck.cards.sumOf { card ->
        Card.valueOf(card).number
    }

    fun addCard(card: List<Card>) {
        deck.addCard(card)
    }

    private fun countAce(): Int = deck.cards.count { card ->
        Card.valueOf(card) == CardNumber.ACE
    }

    companion object {
        private const val BLACK_JACK_LESS_TEN = 12
        private const val ACE_CARD_PLUS_TEN = 10
        private const val NO_ACE = 0
    }
}
