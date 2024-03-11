package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {
    fun judge(participant: Participant): GameResult {
        val dealerScore = this.getCardSum()
        val playerScore = participant.getCardSum()
        return when {
            (dealerScore < playerScore) -> {
                GameResult.WIN
            }

            (dealerScore == playerScore) -> {
                GameResult.DRAW
            }

            else -> {
                GameResult.LOSE
            }
        }
    }

    override fun isHitable(): Boolean {
        return getCardSum() < DEALER_HITABLE_THRESHOLD
    }

    companion object {
        private const val DEALER_HITABLE_THRESHOLD = 17
    }
}
