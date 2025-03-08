package blackjack.domain

import blackjack.enums.Result

abstract class Participant {
    val hand = Hand()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Score = hand.calculateScore()

    abstract fun canHit(): Boolean

    abstract fun getResult(otherScore: Score): Result
}
