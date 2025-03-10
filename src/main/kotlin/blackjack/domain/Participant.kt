package blackjack.domain

abstract class Participant {
    val totalSum: Int
        get() = calculateTotalSum()

    val cards: Cards = Cards()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return totalSum > BLACKJACK_BUST_LIMIT
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    private fun calculateTotalSum(): Int {
        var score = cards.sum()
        var aceCount = cards.count()

        while (score > BLACKJACK_BUST_LIMIT && aceCount > 0) {
            score -= ACE_SCORE_DIFFERENCE
            aceCount--
        }

        return score
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
        private const val ACE_HIGH = 11
        private const val ACE_LOW = 1
        private const val ACE_SCORE_DIFFERENCE = ACE_HIGH - ACE_LOW
    }
}
