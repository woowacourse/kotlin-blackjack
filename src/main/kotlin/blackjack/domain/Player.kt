package blackjack.domain

import blackjack.enums.Result

class Player(
    name: String,
) : Participant(name) {
    override fun canHit(): Boolean = !getScore().isBust()

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

    fun playGame(
        deck: Deck,
        onResponse: (Player) -> Boolean,
        onDraw: (Player) -> Unit,
    ) {
        super.playGame(
            deck,
            shouldContinue = { onResponse(this) },
            onDraw = { onDraw(this) },
        )
    }
}
