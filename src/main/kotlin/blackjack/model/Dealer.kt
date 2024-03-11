package blackjack.model

class Dealer(
    val gameInfo: GameInfo = GameInfo(NAME_DEALER),
) : Participant {

    override fun drawCard(generateCard: () -> Card): PickingState {
        if (isDrawAvailable()) {
            gameInfo.addCard(generateCard())
            return PickingState.HIT
        }
        return PickingState.STAND
    }

    override fun drawUntilSatisfaction(
        generateCard: () -> Card,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawCard { generateCard() }
        when (pickingState) {
            PickingState.HIT -> printCards(gameInfo)
            PickingState.STAND -> return
        }
        drawUntilSatisfaction(generateCard, printCards)
    }

    private fun isDrawAvailable(): Boolean = gameInfo.sumCardValues() <= MAXIMUM_DRAW_THRESHOLD

    companion object {
        private const val NAME_DEALER = "딜러"
        private const val MAXIMUM_DRAW_THRESHOLD = 16
    }
}
