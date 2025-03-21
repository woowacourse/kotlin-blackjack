package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result

class Blackjack(override val hand: Hand) : Finished(hand) {
    override fun decideResult(state: PlayingState): Result {
        return when (state) {
            is Blackjack -> Result.PUSH
            else -> Result.WIN
        }
    }

    override fun profit(state: PlayingState): Double {
        return when(state) {
            is Blackjack -> PUSH_MULTIPLIER
            else -> BLACKJACK_MULTIPLIER
        }
    }

    companion object {
        const val BLACKJACK_MULTIPLIER = 1.5
        const val PUSH_MULTIPLIER = 1.0
    }
}
