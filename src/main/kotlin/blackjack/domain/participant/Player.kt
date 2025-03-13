package blackjack.domain.participant

class Player(
    name: String,
) : Participant(name) {
    override fun canHit(): Boolean = !getScore().isBust()

    override fun getProfit(
        other: Participant,
        bettingAmount: Int,
    ): Int {
        val thisScore = getScore()
        val otherScore = other.getScore()

        return when {
            (this.isBlackjack() && !other.isBlackjack()) -> (bettingAmount * 1.5).toInt()
            (thisScore.isBust()) -> -bettingAmount
            (otherScore.isBust()) -> bettingAmount
            thisScore > otherScore -> bettingAmount
            thisScore < otherScore -> -bettingAmount
            else -> 0
        }
    }
}
