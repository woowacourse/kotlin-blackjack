package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    override fun canHit(): Boolean = !isBust()

    override fun getResult(other: Participant): Result {
        if (isBust()) return Result.LOSE
        if (other.isBust()) return Result.WIN

        val playerScore = calculateScore()
        val otherScore = other.calculateScore()
        return Result.from(playerScore, otherScore)
    }
}
