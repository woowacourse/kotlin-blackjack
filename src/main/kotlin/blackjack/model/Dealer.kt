package blackjack.model

class Dealer(override val cardHand: CardHand) : Role(name = "딜러", cardHand) {
    override fun getState(hitCondition: Boolean): CardHandState {
        val sum = cardHand.sum

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            sum < 16 -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }
}
