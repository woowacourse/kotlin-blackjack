package blackjack.model

class Dealer(
    val gameInfo: GameInfo = GameInfo(NAME_DEALER),
) : Participant {
    override fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawSingleCard(generateCard)
        when (pickingState) {
            PickingState.HIT -> printCards(gameInfo)
            PickingState.STAND -> return
        }
        drawCardsUntilStand(generateCard, printCards)
    }

    override fun drawSingleCard(generateCard: () -> Card?): PickingState {
        if (isDrawAvailable()) {
            gameInfo.addCard(generateCard() ?: return PickingState.STAND)
            return PickingState.HIT
        }
        return PickingState.STAND
    }

    private fun isDrawAvailable(): Boolean = gameInfo.sumOfCards <= MAXIMUM_DRAW_THRESHOLD

    companion object {
        private const val NAME_DEALER = "딜러"
        private const val MAXIMUM_DRAW_THRESHOLD = 16

        fun of(generateCard: () -> Card?): Dealer {
            val gameInfo = GameInfo(NAME_DEALER)
            gameInfo.addCard(generateCard() ?: throw IllegalArgumentException())

            return Dealer(gameInfo)
        }
    }
}
