package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

class Stay(
    hand: Hand,
    profit: Profit
) : Finished(hand, profit) {
    // 자신이 스테이일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        return when (opponent.state) {
            is Bust -> {
                opponent.addResult(GameResult(defeat = 1))
                GameResult(win = 1)
            }
            is BlackJack -> {
                profit.lostAllBettingMoney()
                opponent.addResult(GameResult(win = 1))
                GameResult(defeat = 1)
            }
            is Stay -> compareWhenBothStay(opponent)
            is Hit -> GameResult()
            is Running -> GameResult()
        }
    }

    private fun compareWhenBothStay(opponent: BaseHolder): GameResult {
        val myPoint = hand.calculate()
        val opponentPoint = opponent.state.hand.calculate()
        return if (myPoint > opponentPoint) {
            opponent.addResult(GameResult(defeat = 1))
            GameResult(win = 1)
        }
        else if (myPoint < opponentPoint) {
            profit.lostAllBettingMoney()
            opponent.addResult(GameResult(win = 1))
            GameResult(defeat = 1)
        } else {
            profit.giveBackBettingMoney()
            opponent.addResult(GameResult(push = 1))
            GameResult(push = 1)
        }
    }
}
