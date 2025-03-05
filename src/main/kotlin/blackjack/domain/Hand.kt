package blackjack.domain

class Hand(
    private val cards: List<Card>,
) {
    fun calculateScore(): Int {
        val sum = cards.sumOf { it.getNumber() }
        if (hasAce() && (sum + ACE_VALUE_DIFFERENCE <= BLACKJACK_SCORE)) {
            return sum + ACE_VALUE_DIFFERENCE
        }
        return sum
    }

    private fun hasAce(): Boolean = cards.find { it.isAce() } != null

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_VALUE_DIFFERENCE = 10
    }
}
