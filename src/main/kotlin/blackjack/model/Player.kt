package blackjack.model

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > CardHandState.BLACKJACK.precondition -> CardHandState.BURST
            sum == CardHandState.BLACKJACK.precondition -> CardHandState.BLACKJACK
            else -> CardHandState.STAY
        }
    }
}
