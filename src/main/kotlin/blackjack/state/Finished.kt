package blackjack.state

import blackjack.model.BetAmount
import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.Hand

abstract class Finished(private val hand: Hand) : Started(hand) {
    override fun draw(card: Card): BlackjackState = this

    override fun isFinished(): Boolean = true

    override fun stay(): BlackjackState = Stay(hand)

    abstract fun earningRate(): Double

    override fun profit(
        betAmount: BetAmount,
        gameResult: GameResult,
    ): Double {
        val rate = earningRate()
        return when (gameResult) {
            is GameResult.Draw -> 0.0
            is GameResult.Lose -> -betAmount.amount.toDouble()
            else -> betAmount.amount * rate
        }
    }
}
