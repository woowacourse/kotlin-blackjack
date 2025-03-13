package blackjack.domain.participant

import blackjack.domain.BettingAmount
import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Hand

abstract class Participant(
    val name: String,
) {
    val hand = Hand()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun getScore(): Score = hand.calculateScore()

    fun isBlackjack(): Boolean = hand.isBlackjack()

    fun getProfit(
        other: Participant,
        bettingAmount: BettingAmount,
    ): Double {
        val result = getResult(other)
        return bettingAmount.value * result.rate
    }

    abstract fun canHit(): Boolean

    protected abstract fun getResult(other: Participant): GameResult
}
