package blackjack.model

interface Participant {
    fun drawCard(generateCard: () -> Card): PickingState

    fun drawUntilSatisfaction(
        generateCard: () -> Card,
        printCards: (GameInfo) -> Unit,
    )
}
