package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class Stay(hand: Hand) : Finished(hand) {
    // 자신이 스테이일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        return when (opponent.state) {
            is Bust -> GameResult(win = 1)
            is BlackJack -> GameResult(defeat = 1)
            is Stay -> compareWhenBothStay(opponent.state.hand.calculate())
            is Hit -> GameResult()
            is Running -> GameResult()
        }
    }

    private fun compareWhenBothStay(opponentPoint: Int): GameResult {
        val myPoint = hand.calculate()
        return if (myPoint > opponentPoint) GameResult(win = 1)
        else if (myPoint < opponentPoint) GameResult(defeat = 1)
        else GameResult(push = 1)
    }
}
