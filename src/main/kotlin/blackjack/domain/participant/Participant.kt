package blackjack.domain.participant

import blackjack.domain.Result
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

    abstract fun canHit(): Boolean

    abstract fun getResult(other: Participant): Result
}
