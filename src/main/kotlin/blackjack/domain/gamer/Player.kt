package blackjack.domain.gamer

import blackjack.domain.result.GameResult

class Player(name: String) : Gamer(name) {
    lateinit var money: Money

    fun isAbleToHit(): Boolean {
        return !state.isFinish()
    }

    fun stay() {
        state = state.stay()
    }

    fun result(dealerScore: Score): GameResult {
        return state.result(dealerScore)
    }

    fun profit(dealer: Dealer) : Money {
        return money * state.earningRate(dealer)
    }
}
