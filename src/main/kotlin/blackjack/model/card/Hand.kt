package blackjack.model.card

class Hand(
    private val _cards: MutableList<Card>,
    val stay: Boolean = false,
) {
    val cards: List<Card> get() = _cards.toList()
    val totalScore: Int get() = calculateTotalScore()

    fun draw(card: Card): Hand {
        if (stay) return this
        return Hand(_cards.apply { add(card) }, stay)
    }

    fun decideStay(): Hand {
        return Hand(_cards, true)
    }

    private fun calculateTotalScore(): Int {
        var score = _cards.sumOf { it.denomination.score }
        var aceCount = _cards.count { it.denomination == Denomination.ACE }
        while (aceCount > 0 && score > BLACKJACK_SCORE) {
            score -= CONVERT_ACE
            aceCount--
        }
        return score
    }

    fun isBust(): Boolean = totalScore > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = totalScore == BLACKJACK_SCORE && _cards.size == 2

    fun isStay(): Boolean = stay

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val CONVERT_ACE = 10
    }
}
