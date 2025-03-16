package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Participant
import blackjack.domain.Result

class Bust(override val hand: Hand) : Finished(hand, BUST_PROFIT) {
    companion object {
        private const val BUST_PROFIT = -1.0
    }

    override fun decideResult(dealer: Dealer): Result {
        return Result.LOSE
    }


}
