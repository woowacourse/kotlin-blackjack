package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result

class Stay(override val hand: Hand) : Finished(hand, STAY_PROFIT) {
    companion object {
        private const val STAY_PROFIT = 1.0
    }

    override fun decideResult(dealer: Dealer): Result {
        return when (dealer.state) {
            is Blackjack -> Result.LOSE
            is Bust -> Result.WIN
            else -> compareTo(dealer)
        }
    }

    private fun compareTo(dealer: Dealer): Result {
        return when {
            this.hand.sum() > dealer.hand.sum() -> Result.WIN
            this.hand.sum() < dealer.hand.sum() -> Result.LOSE
            else -> Result.PUSH
        }
    }
}
