package blackjack.state

import blackjack.model.GameResult
import blackjack.model.Hand

class Blackjack(hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = 1.5

    override fun calculate(opponent: BlackjackState): GameResult {
        if (opponent is Blackjack) return GameResult.Draw
        return GameResult.BlackjackWin
    }
}
