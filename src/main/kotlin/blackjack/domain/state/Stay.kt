package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result

class Stay(
    override val hand: Hand,
) : Finished(hand) {
    override fun checkResult(state: State): Result =
        when (state) {
            is Bust -> Result.WIN
            is Blackjack -> Result.LOSE
            else -> compareTo(state)
        }

    override fun profit(state: State): Double =
        when (state) {
            is Bust -> 1.0
            is Blackjack -> -1.0
            else -> decideProfit(state.hand)
        }

    private fun compareTo(state: State): Result =
        when {
            this.hand.getTotalScore() > hand.getTotalScore() -> Result.WIN
            this.hand.getTotalScore() < hand.getTotalScore() -> Result.LOSE
            else -> Result.DRAW
        }

    private fun decideProfit(hand: Hand): Double =
        when {
            this.hand.getTotalScore() > hand.getTotalScore() -> 1.0
            this.hand.getTotalScore() < hand.getTotalScore() -> -1.0
            else -> 0.0
        }

    override fun canDrawCard(): Boolean = false
}
