package blackjack.model

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            else -> CardHandState.STAY
        }
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
