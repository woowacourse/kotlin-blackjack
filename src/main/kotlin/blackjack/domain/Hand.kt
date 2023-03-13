package blackjack.domain

data class Hand(private val cards: List<Card>) {
    val size = cards.size
    operator fun plus(card: Card) = Hand(cards + card)
    fun toList(): List<Card> = cards.toList()
    fun getScore(): Int {
        var score = cards.sumOf { it.value }

        val aceCount = cards.count { it.isAce() }
        repeat(aceCount) { score = adjustAceValue(score) }
        return score
    }

    private fun adjustAceValue(score: Int): Int =
        if (score > BLACKJACK_SCORE) score - GAP_ACE else score

    fun isBlackjack(): Boolean = getScore() == BLACKJACK_SCORE

    fun isBust(): Boolean = getScore() > BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE = 21
        private val GAP_ACE: Int = CardNumber.ACE.value - 1
    }
}
