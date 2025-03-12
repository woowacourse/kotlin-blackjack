package blackjack.domain.model.participant

import blackjack.domain.model.BetAmount
import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Dealer(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
    betAmount: BetAmount,
) : Participant(name, hand, betAmount) {
    fun showFirstCard(): Card = hand.toList().first()

    override fun compareTo(opponent: Participant): GameResult {
        val myScore: Int = hand.getScore()
        val opponentScore: Int = opponent.hand.getScore()

        return when {
            opponent.hand.isBust() -> GameResult.WIN
            hand.isBust() -> GameResult.LOSE
            hand.isBlackJack() && opponent.hand.isNotBlackJack() -> GameResult.WIN
            hand.isBlackJack() && opponent.hand.isBlackJack() -> GameResult.DRAW
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean {
        return !hand.isMoreThan(DEALER_DRAW_CONDITION)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 16
    }
}
