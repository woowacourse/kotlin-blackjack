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

    fun winOverOpponent(opponent: BaseHolder): GameResult {
        opponent.defeat(profit.amount)
        return GameResult.win()
    }

    fun pushWithOpponent(opponent: BaseHolder): GameResult {
        profit.giveBackBettingMoney()
        opponent.push(profit.amount)
        return GameResult.push()
    }

    fun defeatByOpponent(opponent: BaseHolder): GameResult {
        opponent.win(profit.amount)
        return GameResult.defeat()
    }

    override fun changeProfitByOpponent(opponentProfit: Double) {
        profit.calculateProfitByOpponent(opponentProfit)
    }
}
