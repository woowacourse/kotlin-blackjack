package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Dealer(
    name: String = DEFAULT_NAME,
    hand: Hand,
) : Participant(name = name) {
    fun showFirstCard(): Card = hand.toList().first()

    override fun compareTo(opponent: Participant): GameResult {
        val myScore = hand.getScore()
        val opponentScore = opponent.hand.getScore()

        return when {
            opponent.hand.isBust() -> GameResult.WIN
            hand.isBust() -> GameResult.LOSE
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean {
        return hand.isLessOrSameThan(DEALER_DRAW_CONDITION)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 16
    }
}
