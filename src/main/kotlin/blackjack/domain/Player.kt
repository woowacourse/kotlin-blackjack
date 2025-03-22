package blackjack.domain

import blackjack.domain.state.Hit

class Player(name: String, val bettingAmount: Money) : Participant(name) {
    var profit: Double = 0.0
        private set

    override fun calculateProfit(stateProfit: Double) {
        profit = stateProfit * bettingAmount.amount
    }

    override fun canDraw(): Boolean {
        return !state.hand.isBust()
    }

    override fun stay() {
        if (state is Hit) {
            changeState((state as Hit).changeStay())
        }
    }
}
