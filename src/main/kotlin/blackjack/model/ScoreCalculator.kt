package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER
import blackjack.model.card.Card
import blackjack.model.card.Number

object ScoreCalculator {
    private const val ADJUST_ACE_NUMBER = 10

    fun sum(cards: List<Card>) = cards.sumOf { card -> card.number.score }

    fun calculateOptimalSum(cards: List<Card>): Int {
        var totalScore = sum(cards)
        var countAce = countNumber(cards, Number.ACE)
        while (countAce-- > 0) {
            if (totalScore > BUST_NUMBER) totalScore -= ADJUST_ACE_NUMBER
        }
        return totalScore
    }

    private fun countNumber(
        cards: List<Card>,
        targetNumber: Number,
    ): Int {
        return cards.count { card -> card.number == targetNumber }
    }
}
