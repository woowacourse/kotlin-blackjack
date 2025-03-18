package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Stay

class Player(name: String, val bettingAmount: Money) : Participant(name) {
    fun profit(dealer: Dealer): Double {
        return when (state) {
            is Blackjack -> BLACKJACK_MULTIPLIER * bettingAmount.amount
            is Bust -> LOSE_MULTIPLIER * bettingAmount.amount
            else ->
                when ((state as Stay).decideResult(dealer)) {
                    Result.WIN -> WIN_MULTIPLIER * bettingAmount.amount
                    Result.LOSE -> LOSE_MULTIPLIER * bettingAmount.amount
                    Result.PUSH -> PUSH_MULTIPLIER
                }
        }
    }

    companion object {
        const val BLACKJACK_MULTIPLIER = 1.5
        const val WIN_MULTIPLIER = 1.0
        const val LOSE_MULTIPLIER = -1.0
        const val PUSH_MULTIPLIER = 0.0
    }
}
