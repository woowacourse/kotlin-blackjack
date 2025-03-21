package blackjack.domain.state

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

    override fun profit(state: PlayingState): Double {
        return when(state) {
            is Bust -> WIN_MULTIPLIER
            is Blackjack -> LOSE_MULTIPLIER
            else -> stayProfit(state.hand)
        }
    }

    private fun compareTo(state: PlayingState): Result {
        return when {
            this.hand.sum() > state.hand.sum() -> Result.WIN
            this.hand.sum() < state.hand.sum() -> Result.LOSE
            else -> Result.PUSH
        }
    }

    private fun stayProfit(hand: Hand): Double {
        return when {
            this.hand.sum() > hand.sum() -> WIN_MULTIPLIER
            this.hand.sum() < hand.sum() -> LOSE_MULTIPLIER
            else -> PUSH_MULTIPLIER
        }
    }

    companion object {
        const val WIN_MULTIPLIER = 1.0
        const val LOSE_MULTIPLIER = -1.0
        const val PUSH_MULTIPLIER = 0.0
    }
}
