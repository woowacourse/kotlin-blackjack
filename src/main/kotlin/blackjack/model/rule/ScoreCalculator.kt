package blackjack.model.rule

import blackjack.model.card.Card
import blackjack.model.card.CardRank

class ScoreCalculator {
    fun isBust(score: Int): Boolean = score > BUST_CRITERIA

    fun score(cards: List<Card>): Int {
        val hardScore = cards.sumOf { card -> card.rank.score }
        val softScore = softScore(cards, hardScore)

        return when {
            isBust(hardScore) -> hardScore
            isBust(softScore) -> hardScore
            else -> softScore
        }
    }

    private fun softScore(
        cards: List<Card>,
        hardScore: Int,
    ): Int {
        val containsAce = cards.any { card -> card.rank == CardRank.ACE }
        return if (containsAce) hardScore + SOFT_OFFSET_SCORE else hardScore
    }

    companion object {
        private const val BUST_CRITERIA = 21
        private const val SOFT_OFFSET_SCORE = 10
    }
}
