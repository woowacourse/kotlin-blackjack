package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Hand

sealed class Participant(
    val name: String,
) {
    val hand = Hand()

    fun receiveCard(card: Card) {
        hand.addCard(card)
    }

    fun score(): Score = hand.score()

    fun isBlackjack(): Boolean = hand.isBlackjack()

    tailrec fun playGame(
        draw: () -> Card,
        shouldContinue: (Participant) -> Boolean,
        onDraw: (Participant) -> Unit,
    ) {
        if (canHit() && shouldContinue(this)) {
            receiveCard(draw())
            onDraw(this)
            playGame(draw, shouldContinue, onDraw)
        }
    }

    protected fun compare(
        thisScore: Score,
        otherScore: Score,
    ): GameResult =
        when {
            thisScore > otherScore -> GameResult.WIN
            thisScore < otherScore -> GameResult.LOSE
            else -> GameResult.PUSH
        }

    abstract fun canHit(): Boolean

    abstract fun resultAgainst(other: Participant): GameResult
}
