package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

sealed class Progressing(
    override val hand: Hand,
    override val profit: Profit,
) : State {
    override fun updateState(totalPoint: Int): State {
        return if (totalPoint > Hand.BLACKJACK_NUMBER) Bust(hand, profit)
        else if (totalPoint == Hand.BLACKJACK_NUMBER) Stay(hand, profit)
        else Running(hand, profit)
    }

    override fun decideWinner(opponent: BaseHolder): GameResult = GameResult()

    override fun calculateProfitByOpponent(opponentProfit: Double) {
        return
    }
}
