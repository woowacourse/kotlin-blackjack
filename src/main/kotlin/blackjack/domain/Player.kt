package blackjack.domain

class Player(name: String) : Participant(name) {
    fun canHit(): Boolean = this.score < TARGET_SCORE

    fun against(dealer: Dealer): ResultType {
        if (this.isBust()) return ResultType.LOSE
        if (dealer.isBust()) return ResultType.WIN

        val dealerScore = dealer.score
        return when {
            score > dealerScore -> ResultType.WIN
            score == dealerScore -> ResultType.TIE
            else -> ResultType.LOSE
        }
    }
}
