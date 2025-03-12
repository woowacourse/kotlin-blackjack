package blackjack.domain.participant

import blackjack.domain.Result

class Dealer(
    name: String = DEALER_DEFAULT_NAME,
) : Participant(name) {
    override fun canHit(): Boolean = getScore().score <= DEALER_HIT_CONDITION

    override fun getResult(other: Participant): Result {
        val dealerScore = getScore()
        val otherScore = other.getScore()
        if (otherScore.isBust()) return Result.WIN
        if (dealerScore.isBust()) return Result.LOSE
        return when {
            dealerScore > otherScore -> Result.WIN
            dealerScore < otherScore -> Result.LOSE
            else -> Result.PUSH
        }
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_HIT_CONDITION = 16
    }
}
