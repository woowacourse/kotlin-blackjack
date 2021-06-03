package blackjack.domain.gamer

import blackjack.domain.gamer.Score.Companion.DEALER_MUST_HIT_BOUNDARY

class Dealer(name: String = "딜러") : Gamer(name) {
    fun isMustHit(): Boolean {
        return score() <= DEALER_MUST_HIT_BOUNDARY
    }
}
