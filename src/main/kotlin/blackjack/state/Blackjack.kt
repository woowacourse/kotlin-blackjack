package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class Blackjack(hand: Hand) : Finished(hand) {
    override fun earningRate(): Double = 1.5

    override fun calculate(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        if (opponent.getState() is Blackjack) return GameResult.Draw
        return GameResult.BlackjackWin
    }
}
