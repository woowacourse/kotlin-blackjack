package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.result.GameResult

class Dealer : Participant() {
    val name: String = "딜러"

    override fun getFirstOpenCards(): List<Card> = cards.getFirstCard()

    override fun canDraw(): Boolean = cards.calculateTotalScore() < STAY_SCORE

    infix fun judge(player: Player): GameResult {
        val dealerScore = getTotalScore()
        val playerScore = player.getTotalScore()

        return when {
            player.isBust() -> GameResult.LOSE
            dealerScore == playerScore -> GameResult.DRAW
            player.isBlackjack() -> GameResult.BLACKJACK
            isBust() || playerScore > dealerScore -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val STAY_SCORE = 17
    }
}
