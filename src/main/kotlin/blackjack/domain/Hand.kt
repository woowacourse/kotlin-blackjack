package blackjack.domain

class Hand(
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun calculateScore(): Int {
        val sum = _cards.sumOf { it.getNumber() }
        if (hasAce() && (sum + ACE_VALUE_DIFFERENCE <= BLACKJACK_SCORE)) {
            return sum + ACE_VALUE_DIFFERENCE
        }
        return sum
    }

    fun isBust(): Boolean = calculateScore() > BLACKJACK_SCORE

    private fun hasAce(): Boolean = _cards.any { it.isAce() }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_VALUE_DIFFERENCE = 10
    }
}
