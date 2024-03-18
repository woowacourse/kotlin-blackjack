package blackjack.state

import blackjack.model.BetAmount
import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.Hand

interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun hand(): Hand

    fun calculateHand(): Int

    fun isFinished(): Boolean

    fun calculate(opponent: State): GameResult

    fun profit(
        betAmount: BetAmount,
        gameResult: GameResult,
    ): Double
}
