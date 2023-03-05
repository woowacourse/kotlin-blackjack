package blackjack.domain

class Player(name: String) : Participant(name) {
    fun canHit(): Boolean = this.getScore() < TARGET_SCORE
    infix fun against(dealer: Dealer): ResultType {
        if (this.isBust()) return ResultType.LOSE
        if (dealer.isBust()) return ResultType.WIN

        val score = this.getScore()
        val dealerScore = dealer.getScore()
        return when {
            score > dealerScore -> ResultType.WIN
            score == dealerScore -> ResultType.TIE
            else -> ResultType.LOSE
        }
    }
}
