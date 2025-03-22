package blackjack.domain

import blackjack.domain.state.Hit

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    var profit: Double = 0.0
        private set

    override fun calculateProfit(stateProfit: Double) {}

    override fun canDraw(): Boolean {
        return state.hand.sum() < MINIMUM_STAY_CONDITION
    }

    override fun stay() {
        if (state is Hit) {
            changeState((state as Hit).changeStay())
        }
    }

    fun calculateProfitWith(players: List<Player>) {
        players.forEach {
            profit -= it.profit
        }
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val MINIMUM_STAY_CONDITION = 17
    }
}
