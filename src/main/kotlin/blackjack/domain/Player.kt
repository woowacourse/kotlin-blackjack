package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    override fun canHit(): Boolean = !getScore().isBust()

    override fun getResult(otherScore: Score): Result {
        val playerScore = getScore()
        if (playerScore.isBust()) return Result.LOSE
        if (otherScore.isBust()) return Result.WIN
        return Result.from(playerScore, otherScore)
    }
}
