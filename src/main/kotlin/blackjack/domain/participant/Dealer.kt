package blackjack.domain.participant

import blackjack.domain.GameResult

class Dealer(
    name: String = DEALER_DEFAULT_NAME,
) : Participant(name) {
    override fun canHit(): Boolean = getScore().score <= DEALER_HIT_CONDITION

    override fun getResult(other: Participant): GameResult {
        val thisScore = getScore()
        val otherScore = other.getScore()
        return when {
            (!this.isBlackjack() && other.isBlackjack()) -> GameResult.LOSE_BLACKJACK
            (otherScore.isBust()) -> GameResult.WIN
            (thisScore.isBust()) -> GameResult.LOSE
            thisScore > otherScore -> GameResult.WIN
            thisScore < otherScore -> GameResult.LOSE
            else -> GameResult.PUSH
        }
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_HIT_CONDITION = 16
    }
}
