package blackjack.domain.participant

import blackjack.domain.Result
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand

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
