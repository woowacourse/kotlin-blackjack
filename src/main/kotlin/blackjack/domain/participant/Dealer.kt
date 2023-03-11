package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.result.GameResult

class Dealer : Participant(DEALER_NAME) {
    override fun getFirstOpenCards(): List<Card> = cards.getFirstCard()

    override fun canDraw(): Boolean = cards.calculateTotalScore() < STAY_SCORE

    infix fun judge(player: BettingPlayer): GameResult {
        val dealerScore = getTotalScore()
        val playerScore = player.user.getTotalScore()

        return when {
            player.isBust() -> GameResult.LOSE
            dealerScore == playerScore -> GameResult.DRAW
            player.isBlackJack() -> GameResult.BLACKJACK
            isBust() || playerScore > dealerScore -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val STAY_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
