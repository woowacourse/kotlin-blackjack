package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

data class Player(
    val name: String,
    override var state: State,
    val getMoreCard: (String) -> Boolean,
) : Participant {
    override fun drawCard(giveCard: () -> Card) {
        repeat(2) {
            state = state.draw(giveCard())
        }
    }

    override fun drawMoreCard(
        giveCards: () -> Card,
        printCards: (Participant) -> Unit,
    ) {
        while (state == Hit(state.hand) && getMoreCard(name)) {
            state = state.draw(giveCards())
            printCards(this)
        }
    }
}
