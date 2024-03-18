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
            is Bust -> makeOpponentLoser(opponent)
            is BlackJack -> makeOpponentWinner(opponent)
            is Stay -> compareWhenBothStay(opponent)
            is Hit -> GameResult()
            is Running -> GameResult()
        }
    }

    private fun compareWhenBothStay(opponent: BaseHolder): GameResult {
        val myPoint = hand.calculate()
        val opponentPoint = opponent.state.hand.calculate()
        return if (myPoint > opponentPoint) makeOpponentLoser(opponent)
        else if (myPoint < opponentPoint) makeOpponentWinner(opponent)
        else push(opponent)
    }
}
