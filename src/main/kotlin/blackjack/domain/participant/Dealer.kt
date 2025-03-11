package blackjack.domain.participant

import blackjack.domain.Result
import blackjack.domain.Score
import blackjack.domain.card.Deck

class Dealer(
    name: String = DEALER_DEFAULT_NAME,
) : Participant(name) {
    override fun canHit(): Boolean = getScore().score <= DEALER_HIT_CONDITION

    override fun playGame(
        deck: Deck,
        onDraw: (Participant) -> Unit,
        shouldContinue: () -> Boolean,
    ) {
        while (canHit()) {
            drawCard(deck.pick())
            onDraw(this)
        }
    }

    override fun getResult(otherScore: Score): Result {
        val dealerScore = getScore()
        if (otherScore.isBust()) return Result.WIN
        if (dealerScore.isBust()) return Result.LOSE
        return when {
            dealerScore > otherScore -> Result.WIN
            dealerScore < otherScore -> Result.LOSE
            else -> Result.PUSH
        }
    }

    companion object {
        private const val DEALER_DEFAULT_NAME = "딜러"
        private const val DEALER_HIT_CONDITION = 16
    }
}
