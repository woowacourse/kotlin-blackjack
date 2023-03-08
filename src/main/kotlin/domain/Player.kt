package domain

import domain.card.Card
import domain.card.CardNumber

abstract class Player(
    val name: String,
    private val hand: Hand,
) {

    val cards: List<Card> get() = hand.cards.toList()

    fun addScoreTenIfHasAce(): Int {
        if ((calculateCardValueSum() <= BLACK_JACK_LESS_TEN) and (hasAceCard())) {
            return calculateCardValueSum() + ACE_CARD_PLUS_TEN
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = hand.cards.sumOf { card ->
        Card.valueOf(card).number
    }

    fun addCard(card: List<Card>) {
        hand.drawOneCard(card)
    }

    private fun hasAceCard(): Boolean = hand.cards.any { card ->
        card.cardNumber == CardNumber.ACE
    }

    companion object {
        private const val BLACK_JACK_LESS_TEN = 11
        private const val ACE_CARD_PLUS_TEN = 10
    }
}
