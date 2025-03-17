package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Stay

class Player(name: String) : Participant(name) {
    var bettingAmount = 0
        private set

    fun bet(bettingAmount: Int) {
        require(bettingAmount > 0) { "배팅금은 0보다 커야 합니다" }
        this.bettingAmount = bettingAmount
    }

    fun profit(dealer: Dealer): Double {
        return when (state) {
            is Blackjack -> 1.5 * bettingAmount
            is Bust -> -1.0 * bettingAmount
            is Stay ->
                when ((state as Stay).decideResult(dealer)) {
                    Result.WIN -> 1.0 * bettingAmount
                    Result.LOSE -> -1.0 * bettingAmount
                    Result.PUSH -> 0.0
                }
            else -> {
                0.01
            }
        }
    }
}
