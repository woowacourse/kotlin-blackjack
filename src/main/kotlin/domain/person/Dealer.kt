package domain.person

import domain.card.Card
import domain.card.Hand
import domain.state.State
import domain.state.dealerState.DealerFirstTurn

class Dealer(name: String = "딜러") : Person(name) {

    override var state: State = DealerFirstTurn(Hand())
    fun showOneCard(): List<Card> {
        return state.getHandCards().subList(0, 1)
    }
}
