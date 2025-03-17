package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result

class Stay(override val hand: Hand) : Finished(hand) {
    override fun decideResult(dealer: Dealer): Result {
        return when {
            dealer.isBust() -> Result.WIN
            dealer.state is Bust -> Result.WIN
            dealer.state is Blackjack -> Result.LOSE
            else -> compareTo(dealer)
        }
    }

    private fun compareTo(dealer: Dealer): Result {
        return when {
            this.hand.sum() > dealer.state.hand.sum() -> Result.WIN
            this.hand.sum() < dealer.state.hand.sum() -> Result.LOSE
            else -> Result.PUSH
        }
    }
}
