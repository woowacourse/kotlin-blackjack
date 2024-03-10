package blackjack.model

interface Participant {
    fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    )

    fun drawSingleCard(generateCard: () -> Card?): PickingState
}
