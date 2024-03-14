package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class Stay(private val hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = 1.0

    override fun calculate(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        return when {
            opponent.getState() is Bust || self.getSumOfCards() > opponent.getSumOfCards() -> GameResult.Win
            opponent.getState() is Blackjack || self.getSumOfCards() < opponent.getSumOfCards() -> GameResult.Lose
            else -> GameResult.Draw
        }
    }
}
