package blackjack.model

class Player(
    val gameInfo: GameInfo,
    val onInputDecision: () -> String,
) : CardDrawer {
    override fun drawCard(generateCard: () -> Card): PickingState {
        return when (onInputDecision()) {
            HIT -> {
                gameInfo.addCard(generateCard())
                checkBurst()
            }
            STAY -> PickingState.STOP
            else -> throw IllegalArgumentException(EXCEPTION_PLAYER_INPUT)
        }
    }

    fun initializeCards(generateCard: () -> Card) {
        repeat(2) {
            gameInfo.addCard(generateCard())
        }
    }

    private fun checkBurst(): PickingState {
        return if (gameInfo.total > MAXIMUM_CARD_TOTAL) PickingState.STOP else PickingState.CONTINUE
    }

    companion object {
        private const val MAXIMUM_CARD_TOTAL = 21
        private const val HIT = "y"
        private const val STAY = "n"
        private const val EXCEPTION_PLAYER_INPUT = "y나 n을 입력해야 합니다."
    }
}
