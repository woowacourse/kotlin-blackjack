package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

data class Player(
    val name: String,
    override var state: State,
    val getMoreCard: (String) -> Boolean,
) : Participant {
    fun drawInitialCards(giveCard: () -> Card) {
        repeat(2) {
            state = state.draw(giveCard())
        }
    }

    fun drawAdditionalCards(
        giveCard: () -> Card,
        printCards: (String, Hand) -> Unit,
    ) {
        while (state is Hit && getMoreCard(name)) {
            state = state.draw(giveCard())
            printCards(name, state.hand)
        }
    }

    override fun drawCard(giveCard: () -> Card) {
        drawInitialCards(giveCard)
    }

    override fun drawMoreCard(
        giveCards: () -> Card,
        printCards: (Participant) -> Unit,
    ) {
        drawAdditionalCards(giveCards) { _, hand ->
            printCards(this)
        }
    }
}
