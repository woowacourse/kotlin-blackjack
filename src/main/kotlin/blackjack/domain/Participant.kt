package blackjack.domain

abstract class Participant {
    var totalSum: Int = 0
        private set
    val cards: MutableList<Card> = mutableListOf()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        cards.add(card)
        totalSum = calculateTotalSum()
    }

    fun isBust(): Boolean {
        return totalSum > BLACKJACK_LIMIT
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    private fun calculateTotalSum(): Int {
        var score = cards.sumOf { it.getScore() }
        var aceCount = cards.count { it.rank == Rank.ACE }

        while (score > BLACKJACK_LIMIT && aceCount > 0) {
            score -= ACE_SCORE_DIFFERENCE
            aceCount--
        }

        return score
    }

    companion object {
        const val BLACKJACK_LIMIT = 21
        private const val ACE_HIGH = 11
        private const val ACE_LOW = 1
        private const val ACE_SCORE_DIFFERENCE = ACE_HIGH - ACE_LOW
    }
}
