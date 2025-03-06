package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    fun getResult(dealerScore: Int): Result {
        val playerScore = calculateScore()
        if (isBust() || playerScore < dealerScore) {
            return Result.LOSE
        }
        return Result.WIN
    }
}
