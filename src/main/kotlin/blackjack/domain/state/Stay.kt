package blackjack.domain.state

import blackjack.domain.Hand

class Stay(override val hand: Hand) : Finished(hand, STAY_PROFIT) {
    companion object {
        private const val STAY_PROFIT = 1.0
    }
}
