package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result

class Bust(override val hand: Hand) : Finished(hand) {
    override fun decideResult(state: PlayingState): Result {
        return Result.LOSE
    }
}
