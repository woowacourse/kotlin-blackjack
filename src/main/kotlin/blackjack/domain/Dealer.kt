package blackjack.domain

class Dealer : Participant(DEALER_NAME) {
    override fun getFirstOpenCards(): List<Card> = listOf(cards.getFirstCard())

    override fun canDraw(): Boolean = cards.calculateTotalScore() < STAY_SCORE

    companion object {
        private const val STAY_SCORE = 17
        private const val DEALER_NAME = "딜러"
    }
}
