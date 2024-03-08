package blackjack.model.game

import blackjack.model.card.Denomination
import blackjack.model.card.Hand

object ScoreCalculation {
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
        repeat(aceCount) {
            if (score > 21) {
                score -= 10
            }
        }
        return score
    }
}
