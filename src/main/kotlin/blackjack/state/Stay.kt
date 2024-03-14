package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class Stay(private val hand: Hand) : Finished(hand) {
    override fun calculate(opponent: CardHolder): GameResult {
        return when {
            opponent.getState() is Bust || hand.calculate() > opponent.getSumOfCards() -> GameResult(1, 0)
            opponent.getState() is Blackjack || hand.calculate() < opponent.getSumOfCards() -> GameResult(0, 1)
            else -> GameResult(0, 0)
        }
    }
}
