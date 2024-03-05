package blackjack

class Player(private val name: String, private val cardHand: CardHand) {
    fun getCardHandState(isHit: Boolean): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > BLACKJACK -> CardHandState.BURST
            sum == BLACKJACK -> CardHandState.BLACKJACK
            isHit -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
