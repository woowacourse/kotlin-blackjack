package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card

class Dealer(
    name: String = DEFAULT_NAME,
) : Participant(name = name) {
    fun showFirstCard(): Card = handCards.toList().first()

    override fun compareTo(opponent: Participant): GameResult {
        val myScore = handCards.getScore()
        val opponentScore = opponent.handCards.getScore()

        return when {
            opponent.handCards.isBust() -> GameResult.WIN
            handCards.isBust() -> GameResult.LOSE
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean {
        return handCards.isLessOrSameThan(DEALER_DRAW_CONDITION)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 16
    }
}
