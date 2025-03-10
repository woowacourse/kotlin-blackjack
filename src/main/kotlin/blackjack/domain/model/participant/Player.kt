package blackjack.domain.model.participant

import blackjack.domain.model.GameResult

class Player(
    name: String = DEFAULT_NAME,
) : Participant(name = name) {
    override fun compareTo(opponent: Participant): GameResult {
        val myScore = handCards.getScore()
        val opponentScore = opponent.handCards.getScore()

        return when {
            handCards.isBust() -> GameResult.LOSE
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean = !handCards.isBust()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
