package blackjack.model

abstract class Participant(val gameInfo: GameInfo) {
    fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawSingleCard(generateCard)
        printCards(gameInfo)
        when (pickingState) {
            PickingState.HIT -> drawCardsUntilStand(generateCard, printCards)
            PickingState.STAND -> return
        }
    }

    abstract fun drawSingleCard(generateCard: () -> Card?): PickingState
}
