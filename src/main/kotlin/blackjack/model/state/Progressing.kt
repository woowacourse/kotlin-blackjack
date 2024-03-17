package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand

sealed class Progressing(override val hand: Hand) : State {
    override fun updateState(totalPoint: Int): State {
        return if (totalPoint > Hand.BLACKJACK_NUMBER) Bust(hand)
        else if (totalPoint == Hand.BLACKJACK_NUMBER) Stay(hand)
        else Running(hand)
    }

    override fun decideWinner(opponent: BaseHolder): GameResult = GameResult()
}
