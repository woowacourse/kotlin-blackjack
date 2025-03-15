package blackjack.domain.state

import blackjack.domain.Hand

class Bust(override val hand: Hand) : Finished(hand, BUST_PROFIT) {
    companion object {
        private const val BUST_PROFIT = -1.0
    }
}
