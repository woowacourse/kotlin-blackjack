package blackjack.domain

import blackjack.enums.Result

abstract class Participant(
    val name: String,
) {
    val hand = Hand()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun getScore(): Score = hand.calculateScore()

    abstract fun canHit(): Boolean

    abstract fun getResult(otherScore: Score): Result
}
