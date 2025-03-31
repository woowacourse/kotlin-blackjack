package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result

class Bust(
    override val hand: Hand,
) : Finished(hand) {
    override fun checkResult(state: State): Result = Result.LOSE

    override fun profit(state: State): Double = -1.0

    override fun canDrawCard(): Boolean = false
}
