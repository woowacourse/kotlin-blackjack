package blackjack.model

class Dealer(
    val gameInfo: GameInfo = GameInfo(NAME_DEALER),
) : Participant() {
    override fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawSingleCard(generateCard)
        printCards(gameInfo)
        determineContinuation(pickingState, generateCard, printCards)
    }

    override fun drawSingleCard(generateCard: () -> Card?): PickingState {
        if (isDrawAvailable()) {
            gameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))
            return PickingState.HIT
        }
        return PickingState.STAND
    }

    private fun isDrawAvailable(): Boolean = gameInfo.sumOfCards <= MAXIMUM_DRAW_THRESHOLD

    companion object {
        private const val NAME_DEALER = "딜러"
        private const val MAXIMUM_DRAW_THRESHOLD = 16
        private const val EXCEPTION_NO_CARDS_LEFT = "카드가 모두 소진되었습니다."
        private const val INITIAL_CARD_COUNT = 2

        fun of(generateCard: () -> Card?): Dealer {
            val gameInfo = GameInfo(NAME_DEALER)
            repeat(INITIAL_CARD_COUNT) {
                gameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))
            }
            return Dealer(gameInfo)
        }
    }
}
