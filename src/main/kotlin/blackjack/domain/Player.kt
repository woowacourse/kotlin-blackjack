package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    override fun canHit(): Boolean = !calculateScore().isBust()

    override fun getResult(otherScore: Score): Result {
        val playerScore = calculateScore()
        if (playerScore.isBust()) return Result.LOSE
        if (otherScore.isBust()) return Result.WIN
        return Result.from(playerScore, otherScore)
    }
}
