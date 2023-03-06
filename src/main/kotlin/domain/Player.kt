package domain

import domain.card.Card
import domain.card.CardValue

abstract class Player(
    val name: String,
    cards: List<Card>,
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun actualCardValueSum(): Int {
        if ((calculateCardValueSum() < SUM_CONDITION) and (countAce() != ZERO)) {
            return calculateCardValueSum() + ACE_EXTRA_SCORE
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = _cards.sumOf { Card.valueOf(it) }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    private fun countAce(): Int = _cards.count { card ->
        card.value == CardValue.ACE
    }

    companion object {
        private const val SUM_CONDITION = 10
        private const val ACE_EXTRA_SCORE = 10
        private const val ZERO = 0
    }
}
