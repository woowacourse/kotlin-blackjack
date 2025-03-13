package blackjack.domain.participant

class Dealer(
    name: String = DEALER_DEFAULT_NAME,
) : Participant(name) {
    override fun canHit(): Boolean = getScore().score <= DEALER_HIT_CONDITION

    override fun getProfit(
        other: Participant,
        bettingAmount: Int,
    ): Int {
        val thisScore = getScore()
        val otherScore = other.getScore()

        return when {
            (!this.isBlackjack() && other.isBlackjack()) -> -(bettingAmount * 1.5).toInt()
            (otherScore.isBust()) -> bettingAmount
            (thisScore.isBust()) -> -bettingAmount
            thisScore > otherScore -> bettingAmount
            thisScore < otherScore -> -bettingAmount
            else -> 0
        }
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_HIT_CONDITION = 16
    }
}
