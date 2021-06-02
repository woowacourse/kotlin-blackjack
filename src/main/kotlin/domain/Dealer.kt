package domain

private const val DEALER_STAY_SCORE = 17

class Dealer : Participant(name = "Dealer") {
    override fun isHitStatus(): Boolean {
        return hand.getScore() < DEALER_STAY_SCORE
    }
}