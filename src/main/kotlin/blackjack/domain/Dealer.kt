package blackjack.domain

import blackjack.enums.Result

class Dealer : Participant() {
    override fun canHit(): Boolean = getScore().score <= DEALER_HIT_CONDITION

    override fun getResult(otherScore: Score): Result {
        val dealerScore = getScore()
        if (otherScore.isBust()) return Result.WIN
        if (dealerScore.isBust()) return Result.LOSE
        return Result.from(dealerScore, otherScore)
    }

    fun countCards(): Int = hand.cards.size

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
