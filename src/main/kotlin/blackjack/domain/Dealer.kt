package blackjack.domain

import blackjack.domain.enums.Result

class Dealer : Participant() {
    private val result =
        mutableMapOf(
            Result.WIN to 0,
            Result.LOSE to 0,
        )

    fun updateResult(playerResult: Result) {
        val dealerResult =
            when (playerResult) {
                Result.WIN -> Result.LOSE
                Result.LOSE -> Result.WIN
            }
        result[dealerResult] = result.getValue(dealerResult) + 1
    }

    fun getResultCount(resultType: Result): Int = result.getValue(resultType)
}
