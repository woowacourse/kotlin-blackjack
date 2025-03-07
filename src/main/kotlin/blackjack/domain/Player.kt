package blackjack.domain

import blackjack.enums.Result

class Player(
    val name: String,
) : Participant() {
    fun getResult(dealer: Dealer): Result {
        if (isBust()) {
            return Result.LOSE
        }
        if (dealer.isBust()) {
            return Result.WIN
        }
        val dealerScore = dealer.calculateScore()
        val playerScore = calculateScore()
        return Result.from(playerScore, dealerScore)
    }
}
