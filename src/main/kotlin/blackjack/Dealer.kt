package blackjack

class Dealer(private val cardHand: CardHand) : Participant {
    override fun getCardHandState(isHit: Boolean): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > 21 -> CardHandState.BURST
            sum == 21 -> CardHandState.BLACKJACK
            sum > 16 -> CardHandState.STAY
            else -> CardHandState.HIT
        }
    }
}
