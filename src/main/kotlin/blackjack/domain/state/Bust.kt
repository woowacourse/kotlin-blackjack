package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result

class Bust(override val hand: Hand) : Finished(hand) {
    override fun decideResult(state: PlayingState): Result {
        return Result.LOSE
    }

    override fun profit(state: PlayingState): Double {
        return LOSE_MULTIPLIER
    }

    companion object {
        const val LOSE_MULTIPLIER = -1.0
    }
}
