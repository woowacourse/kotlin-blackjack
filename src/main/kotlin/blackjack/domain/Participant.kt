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
        return totalSum > BUST_THRESHOLD
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    private fun calculateTotalSum(): Int {
        var score = cards.sumOf { it.getScore() }
        var aceCount = cards.count { it.rank == Rank.ACE }

        while (score + ACE_SCORE_DIFFERENCE <= BUST_THRESHOLD && aceCount > 0) {
            score += ACE_SCORE_DIFFERENCE
            aceCount--
        }

        return score
    }

    companion object {
        const val BUST_THRESHOLD = 21
        private const val ACE_HIGH = 11
        private const val ACE_LOW = 1
        private const val ACE_SCORE_DIFFERENCE = ACE_HIGH - ACE_LOW
    }
}
