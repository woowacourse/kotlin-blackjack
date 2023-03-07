package blackjack.domain

class Dealer : Participant(DEALER_NAME) {
    override fun getFirstOpenCards(): List<Card> = hand.getFirstCard()

    override fun canDraw(): Boolean = hand.calculateTotalScore() >= STAY_SCORE

    infix fun judge(playerScore: Int): GameResult {
        val dealerScore = getTotalScore()

        return when {
            playerScore > BlackJack.blackjackScore() -> GameResult.LOSE
            dealerScore > BlackJack.blackjackScore() || playerScore > dealerScore -> GameResult.WIN
            dealerScore == playerScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val STAY_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
