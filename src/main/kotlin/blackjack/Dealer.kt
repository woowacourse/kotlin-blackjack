package blackjack

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card
import blackjack.model.state.Hit
import blackjack.model.state.Ready
import blackjack.model.state.State

class Dealer(
    override val name: String = "딜러",
    override var state: State = Ready(Hand(setOf(), 0)),
) : Participant {

    override fun drawCard(generateCard: () -> Card) {
        repeat(2) {
            state = state.draw(generateCard())
        }
    }

    override fun drawUntilSatisfaction(generateCard: () -> Card, printCards: (Participant) -> Unit) {
        while (state == Hit(state.hand) && state.hand.calculateTotal() <= 16) {
            state = state.draw(generateCard())
            printCards(this)
        }
    }
}
