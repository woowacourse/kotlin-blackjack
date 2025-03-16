package blackjack.domain.participant

import blackjack.domain.GameResult

class Dealer(
    name: String = DEALER_DEFAULT_NAME,
) : Participant(name) {
    override fun canHit(): Boolean = score().score <= DEALER_HIT_CONDITION

    override fun resultAgainst(other: Participant): GameResult {
        val thisScore = score()
        val otherScore = other.score()
        return when {
            (!this.isBlackjack() && other.isBlackjack()) -> GameResult.LOSE_BLACKJACK
            otherScore.isBust() -> GameResult.WIN
            thisScore.isBust() -> GameResult.LOSE
            else -> compare(thisScore, otherScore)
        }
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_HIT_CONDITION = 16
    }
}
