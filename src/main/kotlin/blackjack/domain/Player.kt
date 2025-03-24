package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(
    val name: String,
    var bettingMoney: Int,
) : Participant() {
    var profit: Double = 0.0
        private set

    fun drawInitialCards(deck: Deck) {
        repeat(2) { drawCard(deck.draw()) }
        checkInitialState()
    }

    private fun checkInitialState() {
        if (state is Hit && !state.canDrawCard()) {
            stay()
        }
    }

    fun calculateProfit(dealerState: State) {
        profit = bettingMoney * state.profit(dealerState)
    }

    override fun drawMoreCard(): Boolean = state.canDrawCard()

    override fun stay() {
        if (state is Hit) {
            state = (state as Hit).changeStay()
        }
    }

    fun calculateProfits(dealerState: State) {
        players.forEach { it.calculateProfit(dealerState) }
    }
}
