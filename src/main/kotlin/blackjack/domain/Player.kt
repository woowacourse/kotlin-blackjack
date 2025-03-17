package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Stay

class Player(name: String) : Participant(name) {
    var bettingAmount = 0
        private set

    fun bet(bettingAmount: Int) {
        require(bettingAmount >= MINIMUM_BETTING_AMOUNT) { "배팅금은 0보다 커야 합니다" }
        this.bettingAmount = bettingAmount
    }

    fun profit(dealer: Dealer): Double {
        return when (state) {
            is Blackjack -> BLACKJACK_MULTIPLIER * bettingAmount
            is Bust -> LOSE_MULTIPLIER * bettingAmount
            else ->
                when ((state as Stay).decideResult(dealer)) {
                    Result.WIN -> WIN_MULTIPLIER * bettingAmount
                    Result.LOSE -> LOSE_MULTIPLIER * bettingAmount
                    Result.PUSH -> PUSH_MULTIPLIER
                }
        }
    }

    companion object {
        const val MINIMUM_BETTING_AMOUNT = 1
        const val BLACKJACK_MULTIPLIER = 1.5
        const val WIN_MULTIPLIER = 1.0
        const val LOSE_MULTIPLIER = -1.0
        const val PUSH_MULTIPLIER = 0.0
    }
}
