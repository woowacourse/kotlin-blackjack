package blackjack.model

data class Dealer(override val cardHand: CardHand) : Role(name = "딜러", cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            sum <= 16 -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }
}
