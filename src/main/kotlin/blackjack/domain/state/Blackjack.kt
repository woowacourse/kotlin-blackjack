package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result

class Blackjack(
    override val hand: Hand,
) : Finished(hand) {
    override fun checkResult(state: State): Result =
        when (state) {
            is Blackjack -> Result.DRAW
            else -> Result.WIN
        }

    override fun profit(state: State): Double =
        when (state) {
            is Blackjack -> 1.5
            else -> 1.0
        }

    override fun canDrawCard(): Boolean = false
}
