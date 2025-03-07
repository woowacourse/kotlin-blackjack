package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER

class Player(
    val name: String,
) : Person() {
    fun adjustScore(): Int {
        var sumScore = calculateTotalScore()
        var countAce = countAce()
        while (countAce-- > 0) {
            if (sumScore > BUST_NUMBER) {
                sumScore -= ADJUST_ACE_NUMBER
            }
        }
        return sumScore
    }

    private fun countAce() = super.cards.count { it.number == Number.ACE }

    override fun isBust() = adjustScore() > BUST_NUMBER

    companion object {
        private const val ADJUST_ACE_NUMBER = 10
    }
}
