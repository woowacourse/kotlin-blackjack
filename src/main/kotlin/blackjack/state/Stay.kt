package blackjack.state

import blackjack.model.GameResult
import blackjack.model.Hand

class Stay(private val hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = 1.0

    override fun calculate(opponent: BlackjackState): GameResult {
        return when {
            opponent is Bust || calculateHand() > opponent.calculateHand() -> GameResult.Win
            opponent is Blackjack || calculateHand() < opponent.calculateHand() -> GameResult.Lose
            else -> GameResult.Draw
        }
    }
}
