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

    fun playGame(
        draw: () -> Card,
        shouldContinue: (Participant) -> Boolean,
        onDraw: (Participant) -> Unit,
    ) {
        while (canHit() && shouldContinue(this)) {
            receiveCard(draw())
            onDraw(this)
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
