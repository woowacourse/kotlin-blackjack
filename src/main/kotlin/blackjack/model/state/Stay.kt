package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

class Stay(
    hand: Hand,
    profit: Profit
) : Finished(hand, profit) {
    override fun decideWinner(opponent: BaseHolder): GameResult {
        return when (opponent.state) {
            is Bust -> winOverOpponent(opponent)
            is BlackJack -> {
                profit.lostAllBettingMoney()
                defeatByOpponent(opponent)
            }
            is Stay -> compareWhenBothStay(opponent)
            is Hit -> GameResult()
            is Running -> GameResult()
        }
    }

    private fun compareWhenBothStay(opponent: BaseHolder): GameResult {
        val myPoint = hand.calculate()
        val opponentPoint = opponent.state.hand.calculate()
        return if (myPoint > opponentPoint) winOverOpponent(opponent)
        else if (myPoint < opponentPoint) {
            profit.lostAllBettingMoney()
            defeatByOpponent(opponent)
        }
        else pushWithOpponent(opponent)
    }
}
