package domain.participant

private const val DEALER_STAY_SCORE = 17

class Dealer(hand : Hand = Hand()) : Participant(name = "Dealer", hand) {
    override fun isDrawable(): Boolean {
        return hand.getScore() < DEALER_STAY_SCORE
    }
}
