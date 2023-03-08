package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class Dealer : Participant(DEALER_NAME) {
    override fun getFirstOpenCards(): List<Card> = cards.getFirstCard()

    override fun canDraw(): Boolean = cards.calculateTotalScore() < STAY_SCORE

    infix fun judge(player: BettingPlayer): GameResult {
        val dealerScore = getTotalScore()
        val playerScore = player.getTotalScore()
        val isBlackJack = player.isBlackJack()

        return when {
            playerScore > blackjackScore() -> GameResult.LOSE
            dealerScore == playerScore -> GameResult.DRAW
            isBlackJack -> GameResult.BLACKJACK
            dealerScore > blackjackScore() || playerScore > dealerScore -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val STAY_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
