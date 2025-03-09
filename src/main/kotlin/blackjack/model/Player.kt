package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER
import blackjack.model.card.Number

class Player(
    name: String,
) : Participant(name) {
    fun adjustScore(): Int {
        var totalScore = ScoreCalculator.sum(cards)
        var countAce = countAce()
        while (countAce-- > 0) {
            if (totalScore > BUST_NUMBER) {
                totalScore -= ADJUST_ACE_NUMBER
            }
        }
        return totalScore
    }

    private fun countAce() = cards.count { it.number == Number.ACE }

    override fun isBust() = adjustScore() > BUST_NUMBER

    companion object {
        private const val ADJUST_ACE_NUMBER = 10
    }
}
