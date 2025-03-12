package model

class Dealer(private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getScore() <= STANDARD_DEALER_HIT

    fun playTurn(getCard: () -> List<Card>) {
        while (decideToHit()) {
            receiveCards { getCard() }
        }
    }

    companion object {
        private const val STANDARD_DEALER_HIT = 16
    }
}
