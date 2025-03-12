package model

class Dealer(private val hand: Hand) : Participant(hand) {
    fun getCardNames(): List<String> = hand.handCards.displayNames()

    override fun decideToHit(): Boolean = getScore() <= STANDARD_DEALER_HIT

    companion object {
        private const val STANDARD_DEALER_HIT = 16
    }
}
