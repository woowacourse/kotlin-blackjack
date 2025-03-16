package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result

class Blackjack(override val hand: Hand) : Finished(hand, BLACKJACK_PROFIT) {
    override fun decideResult(dealer: Dealer): Result {
        return when (dealer.state) {
            is Blackjack -> Result.PUSH
            else -> Result.WIN
        }
    }

    companion object {
        private const val BLACKJACK_PROFIT = 1.5
    }
}
