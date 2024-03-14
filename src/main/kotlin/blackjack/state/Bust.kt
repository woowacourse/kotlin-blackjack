package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.GameResult.Lose
import blackjack.model.GameResult.Win
import blackjack.model.Hand

class Bust(hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = -1.0

    override fun calculate(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        if (self is Dealer && opponent.getState() is Bust) return Win
        return Lose
    }
}
