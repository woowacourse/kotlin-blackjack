package domain.participant

import domain.card.Card
import domain.card.Cards
import domain.state.Hit
import domain.state.State

abstract class Participant(val name: Name, bettingMoney: BettingMoney) {
    var state: State = Hit(Cards(listOf()), bettingMoney)
        private set

    fun draw(card: Card) {
        state = state.draw(card = card)
    }

    fun stopDraw() {
        state = state.stay()
    }

    fun getProfit(dealerState: State): Double {
        return state.getProfitRate(dealerState)
    }

    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
}
