package blackjack.state

import blackjack.model.BetAmount
import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

abstract class Running(hand: Hand) : Started(hand) {
    override fun isFinished(): Boolean = false

    override fun calculate(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        return GameResult.Lose
    }

    override fun profit(
        betAmount: BetAmount,
        gameResult: GameResult,
    ): Double = 0.0
}
