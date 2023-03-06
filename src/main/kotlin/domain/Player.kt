package domain

import domain.card.Card
import domain.card.CardNumber

abstract class Player(
    val name: String,
    private val _cards: MutableList<Card>,
) {

    val cards: List<Card> get() = _cards.toList()

    fun validPlayerSum(): Int {
        if ((calculateCardValueSum() < ACE_CARD_PLUS_TEN) and (countAce() != NO_ACE)) {
            return calculateCardValueSum() + ACE_CARD_PLUS_TEN
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) }

    fun addCard(card: List<Card>) {
        _cards.add(card.first())
    }

    private fun countAce(): Int = _cards.count { card ->
        card.cardNumber == CardNumber.ACE
    }

    companion object {
        private const val ACE_CARD_PLUS_TEN = 10
        private const val NO_ACE = 0
    }
}
