package blackjack.state

import blackjack.model.GameResult
import blackjack.model.GameResult.Lose
import blackjack.model.Hand

class Bust(hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = -1.0

    override fun calculate(opponent: BlackjackState): GameResult {
        return Lose
    }
}
