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
        opponent.defeat()
        return GameResult().win()
    }

    fun push(opponent: BaseHolder): GameResult {
        profit.giveBackBettingMoney()
        opponent.setProfitFromOpponent(profit.amount)
        opponent.push()
        return GameResult().push()
    }

    fun makeOpponentWinner(opponent: BaseHolder): GameResult {
        opponent.setProfitFromOpponent(profit.amount)
        opponent.win()
        return GameResult().defeat()
    }

    override fun changeProfitByOpponent(opponentProfit: Double) {
        profit.calculateProfitByOpponent(opponentProfit)
    }
}
