package blackjack.model.card

class Hand(val cards: MutableList<Card>) {
    var totalScore = 0

    fun draw(card: Card) {
        cards.add(card)
        totalScore = calculate()
    }

    private fun calculate(): Int {
        val totalScore = calculateTotalScore(this)
        return convertAceToOne(totalScore, this)
    }

    private fun calculateTotalScore(hand: Hand) = hand.cards.sumOf { card -> card.denomination.score }

    private fun convertAceToOne(
        totalScore: Int,
        hand: Hand,
    ): Int {
        var score = totalScore
        val aceCount = hand.cards.count { it.denomination == Denomination.ACE }
        repeat(aceCount) { if (score > BLACKJACK_SCORE) score -= CONVERT_ACE }
        return score
    }

    fun isBust(): Boolean {
        return totalScore > BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return totalScore == BLACKJACK_SCORE
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val CONVERT_ACE = 10
    }
}
