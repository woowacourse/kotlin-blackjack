package blackjack.domain.model.participant

import blackjack.domain.model.BetAmount
import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Hand

class Player(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
    val betAmount: BetAmount,
) : Participant(name, hand) {
    override fun compareTo(opponent: Participant): GameResult {
        val myScore: Int = hand.getScore()
        val opponentScore: Int = opponent.hand.getScore()

        return when {
            hand.isBust() -> GameResult.LOSE
            hand.isBlackJack() && opponent.hand.isNotBlackJack() -> GameResult.WIN
            hand.isBlackJack() && opponent.hand.isBlackJack() -> GameResult.DRAW
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean = !hand.isBust()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
