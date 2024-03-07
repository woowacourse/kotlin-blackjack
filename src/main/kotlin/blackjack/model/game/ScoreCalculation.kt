package blackjack.model.game

import blackjack.model.card.Hand

object ScoreCalculation {
    fun calculate(hand: Hand): Int {
        var totalScore = calculateTotalScore(hand)
        totalScore = convertAceToOne(totalScore, hand)
        return totalScore
    }

    private fun convertAceToOne(
        totalScore: Int,
        hand: Hand,
    ): Int {
        var score = totalScore
        if (totalScore > 21 && hand.aceCount > 0) {
            hand.aceCount--
            score -= 10
        }
        return score
    }

    private fun calculateTotalScore(hand: Hand) = hand.cards.sumOf { card -> card.denomination.score }
}
