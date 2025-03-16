package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination

class Hand(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card> get() = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        val sum = _cards.sumOf { it.denomination.value }
        if (containsAce() && sum + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
            return sum + ACE_ADDITIONAL_SCORE
        }
        return sum
    }

    fun isBust(): Boolean = sum() > BLACKJACK_SCORE

    fun isBlackjack(): Boolean {
        return containsAce() && _cards.size == BLACKJACK_SIZE && sum() == BLACKJACK_SCORE
    }

    private fun containsAce(): Boolean = _cards.any { it.denomination == Denomination.ACE }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val BLACKJACK_SIZE = 2
        private const val ACE_ADDITIONAL_SCORE = 10
    }
}