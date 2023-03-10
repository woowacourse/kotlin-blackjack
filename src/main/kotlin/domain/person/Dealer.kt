package domain.person

import domain.card.Card
import domain.state.DealerFirstTurn
import domain.state.State

class Dealer(card1: Card, card2: Card) : Person() {
    override val name: String = DEALER
    override var state: State = DealerFirstTurn(card1, card2)

    fun showFirstCard(): List<Card> = state.handOfCards.showFirstCard()

    companion object {
        const val DEALER = "딜러"
    }
}
