package blackjack.domain.participant

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

    fun score(): Score = hand.score()

    fun isBlackjack(): Boolean = hand.isBlackjack()

    fun playGame(
        draw: () -> Card,
        shouldContinue: (Participant) -> Boolean,
        onDraw: (Participant) -> Unit,
    ) {
        while (canHit() && shouldContinue(this)) {
            drawCard(draw())
            onDraw(this)
        }
    }

    abstract fun canHit(): Boolean

    abstract fun getResult(other: Participant): GameResult
}
