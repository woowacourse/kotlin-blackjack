package blackjack.model.game

import blackjack.model.card.Denomination
import blackjack.model.card.Hand

object ScoreCalculation {
    const val BLACKJACK_SCORE = 21
    private const val CONVERT_ACE = 10

    fun calculate(hand: Hand): Int {
        val totalScore = calculateTotalScore(hand)
        return convertAceToOne(totalScore, hand)
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
}
