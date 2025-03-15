package blackjack.domain.state

import blackjack.domain.Hand

class Blackjack(override val hand: Hand) : Finished(hand, BLACKJACK_PROFIT) {
    companion object {
        private const val BLACKJACK_PROFIT = 1.5
    }
}
