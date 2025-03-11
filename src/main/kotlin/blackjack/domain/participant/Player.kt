package blackjack.domain.participant

import blackjack.domain.Result
import blackjack.domain.Score
import blackjack.domain.card.Deck

class Player(
    name: String,
    private val bettingAmount: Int,
) : Participant(name) {
    override fun canHit(): Boolean = !getScore().isBust()

    override fun playGame(
        deck: Deck,
        onDraw: (Participant) -> Unit,
        shouldContinue: () -> Boolean,
    ) {
        while (canHit() && shouldContinue()) {
            drawCard(deck.pick())
            onDraw(this)
        }
    }

    override fun getResult(otherScore: Score): Result {
        val playerScore = getScore()
        if (playerScore.isBust()) return Result.LOSE
        if (otherScore.isBust()) return Result.WIN
        return when {
            playerScore > otherScore -> Result.WIN
            playerScore < otherScore -> Result.LOSE
            else -> Result.PUSH
        }
    }
}
