package blackjack.model

class Dealer(
    val dealerGameInfo: GameInfo = GameInfo(NAME_DEALER),
) : Participant(dealerGameInfo) {
    override fun drawSingleCard(generateCard: () -> Card?): PickingState {
        if (isDrawAvailable()) {
            dealerGameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))
            return PickingState.HIT
        }
        return PickingState.STAND
    }

    private fun isDrawAvailable(): Boolean = dealerGameInfo.sumOfCards <= MAXIMUM_DRAW_THRESHOLD

    companion object {
        private const val NAME_DEALER = "딜러"
        private const val MAXIMUM_DRAW_THRESHOLD = 16
        private const val EXCEPTION_NO_CARDS_LEFT = "카드가 모두 소진되었습니다."

        fun of(generateCard: () -> Card?): Dealer {
            val gameInfo = GameInfo(NAME_DEALER)
            gameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))

            return Dealer(gameInfo)
        }
    }
}
