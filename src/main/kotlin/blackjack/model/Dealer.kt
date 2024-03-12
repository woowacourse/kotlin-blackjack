package blackjack.model

class Dealer(
    val dealerGameInfo: GameInfo = GameInfo(NAME_DEALER),
) : Participant(dealerGameInfo) {
    override fun drawSingleCard(generateCard: () -> Card?): PickingState {
        if (isDrawAvailable()) {
            dealerGameInfo.addCard(generateCard() ?: return PickingState.STAND)
            return PickingState.HIT
        }
        return PickingState.STAND
    }

    private fun isDrawAvailable(): Boolean = dealerGameInfo.sumOfCards <= MAXIMUM_DRAW_THRESHOLD

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
