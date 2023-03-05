package blackjack.domain

class Player(name: ParticipantName) : Participant(name) {
    constructor(name: String) : this(ParticipantName(name))
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

    override fun equals(other: Any?): Boolean = if (other is Player) name == other.name else false
    override fun hashCode(): Int = this.name.hashCode()
}
