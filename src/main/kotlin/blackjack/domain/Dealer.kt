package blackjack.domain

class Dealer : Participant() {
    override fun canHit(): Boolean = calculateScore() <= DEALER_HIT_CONDITION

    fun countCards(): Int = hand.cards.size

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
