package blackjack.domain

import blackjack.domain.state.State

interface Participant {
    val state: State

    fun drawCard(giveCard: () -> Card)

    fun drawMoreCard(
        giveCards: () -> Card,
        printCards: (Participant) -> Unit,
    )
}
