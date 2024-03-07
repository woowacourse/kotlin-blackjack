package blackjack.model

class Dealer (name: String = "딜러") : Participant(name) {
    fun judge(participant: Participant): GameResult {
        val dealerScore = this.getCardSum()
        val playerScore = participant.getCardSum()
        return when {
            (dealerScore < playerScore) -> {
                GameResult.`승`
            }

            (dealerScore == playerScore) -> {
                GameResult.`무`
            }

            else -> {
                GameResult.`패`
            }
        }
    }

    override fun isHitable(): Boolean {
        val score = getCardSum()
        val threshold = 17
        return score < threshold
    }
}
