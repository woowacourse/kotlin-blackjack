package blackjack.domain

import blackjack.domain.state.Ready
import blackjack.domain.state.State

class Dealer(
    override var state: State = Ready(Hand(emptyList(), 0)),
) : Participant {
    override fun drawCard(giveCard: () -> Card) {
        repeat(2) {
            state = state.draw(giveCard())
        }
    }

    override fun drawMoreCard(
        giveCard: () -> Card,
        printCards: (Participant) -> Unit,
    ) {
        while (state.canDrawCard()) {
            state = state.draw(giveCard())
            printCards(this)
        }
    }
}
