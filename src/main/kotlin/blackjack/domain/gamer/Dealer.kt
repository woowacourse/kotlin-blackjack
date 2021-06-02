package blackjack.domain.gamer

class Dealer(name: String = "딜러", hand: Hand = Hand()) : Gamer(name, hand) {
    fun isMustHit(): Boolean {
        return score() <= DEALER_MUST_HIT_BOUNDARY
    }

    companion object {
        private const val DEALER_MUST_HIT_BOUNDARY = 16
    }
}
