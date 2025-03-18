package blackjack.domain.participant

import blackjack.domain.GameResult

class Player(
    name: String,
) : Participant(name) {
    override fun canHit(): Boolean = !score().isBust()

    override fun resultAgainst(other: Participant): GameResult {
        val thisScore = score()
        val otherScore = other.score()
        return when {
            (this.isBlackjack() && !other.isBlackjack()) -> GameResult.WIN_BLACKJACK
            thisScore.isBust() -> GameResult.LOSE
            otherScore.isBust() -> GameResult.WIN
            else -> compare(thisScore, otherScore)
        }
    }
}
