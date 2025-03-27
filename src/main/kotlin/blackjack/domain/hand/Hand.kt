package blackjack.domain.hand

import blackjack.domain.card.Card

class Hand(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    fun cards(): List<Card> = cards.toList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return totalSum() > BUST_THRESHOLD
    }

    fun isBlackJack(): Boolean {
        return cards.size == BLACKJACK_CONDITION_NUMBER && totalSum() == BLACKJACK_CONDITION_SUM
    }

    fun canHit(hitThreshold: Int): Boolean {
        return totalSum() <= hitThreshold
    }

    fun totalSum(): Int {
        var sum = cards.sumOf { it.getScore() }

        if (hasAce() && sum + ACE_BONUS_SCORE <= BUST_THRESHOLD) {
            sum += ACE_BONUS_SCORE
        }
        return sum
    }

    private fun hasAce(): Boolean {
        return cards.any { it.isAce() }
    }

    companion object {
        private const val BLACKJACK_CONDITION_NUMBER = 2
        private const val BLACKJACK_CONDITION_SUM = 21
        private const val BUST_THRESHOLD = 21
        private const val ACE_BONUS_SCORE = 10
    }
}
