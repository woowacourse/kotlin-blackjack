package domain

import domain.card.Card
import domain.card.CardValue

class Cards(cards: List<Card>) {
    private val _value: MutableList<Card> = cards.toMutableList()
    val value: List<Card> get() = _value.toList()

    fun actualCardValueSum(): Int {
        if ((calculateCardValueSum() <= SUM_CONDITION) and (countAce() != ZERO)) {
            return calculateCardValueSum() + ACE_EXTRA_SCORE
        }

        return calculateCardValueSum()
    }

    fun calculateCardValueSum(): Int = _value.sumOf { card ->
        card.value.number
    }

    fun addCard(card: Card) {
        _value.add(card)
    }

    private fun countAce(): Int = _value.count { card ->
        card.value == CardValue.ACE
    }

    fun isBlackJack(): Boolean = (_value.size == NUMBER_OF_BLACKJACK_CARDS) && (actualCardValueSum() == BLACKJACK_SCORE)

    companion object {
        private const val SUM_CONDITION = 11
        private const val ACE_EXTRA_SCORE = 10
        private const val NUMBER_OF_BLACKJACK_CARDS = 2
        private const val BLACKJACK_SCORE = 21
        private const val ZERO = 0
    }
}
