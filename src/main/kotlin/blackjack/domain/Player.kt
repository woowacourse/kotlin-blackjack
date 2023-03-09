package blackjack.domain

class Player(name: ParticipantName) : Participant(name) {
    constructor(name: String) : this(ParticipantName(name))
    infix fun against(dealer: Dealer): ResultType {
        if (dealer.isBust()) return ResultType.WIN

        val score = this.getScore()
        val dealerScore = dealer.getScore()
        return when {
            score > dealerScore -> ResultType.WIN
            score == dealerScore -> ResultType.TIE
            else -> ResultType.LOSE
        }
    }
    fun isBlackjack(): Boolean = cards.size == 2 && getScore() == TARGET_SCORE

    override fun equals(other: Any?): Boolean = if (other is Player) name == other.name else false
    override fun hashCode(): Int = this.name.hashCode()
}
