package blackjack.domain.participant

import blackjack.domain.BettingAmount
import blackjack.domain.GameResult
import blackjack.domain.Profit
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

    fun score(): Score = hand.score()

    fun isBlackjack(): Boolean = hand.isBlackjack()

    fun getProfit(
        other: Participant,
        bettingAmount: BettingAmount,
    ): Profit {
        val result = getResult(other)
        return Profit(bettingAmount.value * result.rate)
    }

    abstract fun canHit(): Boolean

    protected abstract fun getResult(other: Participant): GameResult
}
