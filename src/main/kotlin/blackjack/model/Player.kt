package blackjack.model

data class Player(override val name: String, override val cardHand: CardHand) : Role(name, cardHand) {
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
