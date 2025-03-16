package blackjack.domain

class Hand(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    fun getCards(): List<Card> = cards.toList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return getTotalSum() > BUST_THRESHOLD
    }

    fun isBlackJack(): Boolean {
        return cards.size == BLACKJACK_CONDITION_NUMBER && getTotalSum() == BLACKJACK_CONDITION_SUM
    }

    fun canHit(hitThreshold: Int): Boolean {
        return getTotalSum() <= hitThreshold
    }

    fun getTotalSum(): Int {
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
