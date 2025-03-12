package blackjack.domain.participant

import blackjack.domain.Result

class Player(
    name: String,
    private val bettingAmount: Int,
) : Participant(name) {
    override fun canHit(): Boolean = !getScore().isBust()

    override fun getResult(other: Participant): Result {
        if (isBlackjack() && !other.isBlackjack()) return Result.BLACKJACK

        val playerScore = getScore()
        val otherScore = other.getScore()
        if (playerScore.isBust()) return Result.LOSE
        if (otherScore.isBust()) return Result.WIN
        return when {
            playerScore > otherScore -> Result.WIN
            playerScore < otherScore -> Result.LOSE
            else -> Result.PUSH
        }
    }

    fun getProfit(result: Result): Double =
        when (result) {
            Result.BLACKJACK -> bettingAmount * 1.5
            Result.WIN -> bettingAmount.toDouble()
            Result.LOSE -> -bettingAmount.toDouble()
            Result.PUSH -> 0.0
        }
}
