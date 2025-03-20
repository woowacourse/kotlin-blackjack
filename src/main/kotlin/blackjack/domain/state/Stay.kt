package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result

class Stay(override val hand: Hand) : Finished(hand) {
    override fun decideResult(state: PlayingState): Result {
        return when (state) {
            is Bust -> Result.WIN
            is Blackjack -> Result.LOSE
            else -> compareTo(state)
        }
    }

    private fun compareTo(state: PlayingState): Result {
        return when {
            this.hand.sum() > state.hand.sum() -> Result.WIN
            this.hand.sum() < state.hand.sum() -> Result.LOSE
            else -> Result.PUSH
        }
    }
}
