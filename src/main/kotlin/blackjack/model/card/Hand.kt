package blackjack.model.card

class Hand(private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card> get() = _cards
    val totalScore: Int
        get() = calculateTotalScore()

    fun draw(card: Card) {
        _cards.add(card)
    }

    private fun calculateTotalScore(): Int {
        var score = cards.sumOf { it.denomination.score }
        var aceCount = cards.count { it.denomination == Denomination.ACE }

        while (aceCount > 0 && score > BLACKJACK_SCORE) {
            score -= CONVERT_ACE
            aceCount--
        }
        return score
    }

    fun isBust(): Boolean = totalScore > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = totalScore == BLACKJACK_SCORE && cards.size == 2

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val CONVERT_ACE = 10
    }
}
