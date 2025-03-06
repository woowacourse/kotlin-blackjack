package blackjack.domain.model

open class Player(val name: String, val cards: MutableList<Card> = mutableListOf()) {
    fun accept(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun getScore(): Int {
        val score = cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce() = cards.any { it.rank == Rank.ACE }

    companion object {
        private const val MAX_BONUS_SCORE = 11
        private const val BONUS_SCORE = 10
    }
}
