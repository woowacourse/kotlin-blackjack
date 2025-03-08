package blackjack.domain

import blackjack.enums.Result

class Dealer : Participant() {
    override fun canHit(): Boolean = calculateScore() <= DEALER_HIT_CONDITION

    override fun getResult(other: Participant): Result {
        if (other.isBust()) return Result.WIN
        if (isBust()) return Result.LOSE

        val dealerScore = calculateScore()
        val otherScore = other.calculateScore()
        return Result.from(dealerScore, otherScore)
    }

    fun countCards(): Int = hand.cards.size

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
