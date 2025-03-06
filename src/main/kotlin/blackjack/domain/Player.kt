package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    fun getResult(dealer: Dealer): Result {
        val dealerScore = if (dealer.isBust()) 0 else dealer.calculateScore()
        val playerScore = calculateScore()
        if (isBust() || playerScore < dealerScore) {
            return Result.LOSE
        }
        return Result.WIN
    }
}
