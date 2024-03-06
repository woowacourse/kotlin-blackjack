package blackjack.model

class Dealer : Participant() {

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

    fun isHitable(): Boolean {
        val score = getCardSum()
        val threshold = 17
        return score < threshold
    }
}
