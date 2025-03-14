package model

class Dealer(private val hand: Hand) : Participant(hand) {
    override fun decideToHit(): Boolean = getTotalScore() <= STANDARD_DEALER_HIT

    fun playTurn(getCard: () -> List<Card>) {
        while (decideToHit() && !isBlackJack()) {
            receiveCards { getCard() }
        }
    }

    companion object {
        private const val STANDARD_DEALER_HIT = 16
    }
}
