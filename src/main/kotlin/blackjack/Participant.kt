package blackjack

import blackjack.model.entitiy.Card
import blackjack.model.state.State

interface Participant {
    val name: String
    val state: State

    fun drawCard(generateCard: () -> Card)

    fun drawUntilSatisfaction(
        generateCard: () -> Card,
        printCards: (Participant) -> Unit,
    )
}
