package blackjack.state

import blackjack.model.BetAmount
import blackjack.model.Card
import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

interface BlackjackState {
    fun draw(card: Card): BlackjackState

    fun stay(): BlackjackState

    fun hand(): Hand

    fun calculateHand(): Int

    fun isFinished(): Boolean

    fun calculate(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult

    fun profit(
        betAmount: BetAmount,
        gameResult: GameResult,
    ): Double
}
