package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

sealed class Finished(
    override val hand: Hand,
    override val profit: Profit,
) : State {
    override fun getCard(card: Card): State = this

    override fun updateState(totalPoint: Int): State = this

    override fun hitOrStay(isHit: Boolean): State = this

    fun makeOpponentLoser(opponent: BaseHolder): GameResult {
        opponent.setProfitFromOpponent(profit.amount)
        opponent.addResult(GameResult(defeat = 1))
        return GameResult(win = 1)
    }

    fun push(opponent: BaseHolder): GameResult {
        profit.giveBackBettingMoney()
        opponent.setProfitFromOpponent(profit.amount)
        opponent.addResult(GameResult(push = 1))
        return GameResult(push = 1)
    }

    fun makeOpponentWinner(opponent: BaseHolder): GameResult {
        profit.lostAllBettingMoney()
        opponent.setProfitFromOpponent(profit.amount)
        opponent.addResult(GameResult(win = 1))
        return GameResult(defeat = 1)
    }

    override fun changeProfitByOpponent(opponentProfit: Double) {
        profit.calculateProfitByOpponent(opponentProfit)
    }
}
