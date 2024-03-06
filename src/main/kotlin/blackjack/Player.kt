package blackjack

class Player(private val name: String, override val cardHand: CardHand) : Role(cardHand) {
    override fun getState(hitCondition: Boolean): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            hitCondition -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
