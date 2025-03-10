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

    fun playGame(
        deck: Deck,
        shouldContinue: () -> Boolean,
        onDraw: () -> Unit,
    ) {
        while (canHit() && shouldContinue()) {
            drawCard(deck.pick())
            onDraw()
        }
    }

    abstract fun canHit(): Boolean

    abstract fun getResult(otherScore: Score): Result
}
