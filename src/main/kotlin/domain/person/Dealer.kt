package domain.person

import domain.card.Card
import domain.card.HandOfCards
import domain.state.DealerFirstTurn
import domain.state.State

class Dealer() : Person() {
    override val name: String = DEALER
    override var state: State = DealerFirstTurn(HandOfCards())

    fun showFirstCard(): List<Card> = state.handOfCards.showFirstCard()

    companion object {
        const val DEALER = "딜러"
    }
}
