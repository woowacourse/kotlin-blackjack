package blackjack

class Dealer(private val cardHand: CardHand) : Role(cardHand) {
    override fun getState(hitCondition: Boolean): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            sum < 16 -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }
}
