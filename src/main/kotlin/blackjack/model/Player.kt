package blackjack.model

class Player(
    val gameInfo: GameInfo,
    val onInputDecision: () -> String,
) : Participant {
    override fun drawCard(generateCard: () -> Card): PickingState {
        return when (onInputDecision()) {
            HIT -> {
                gameInfo.addCard(generateCard())
                checkBurst()
            }

            STAY -> PickingState.STAND
            else -> throw IllegalArgumentException(EXCEPTION_PLAYER_INPUT)
        }
    }

    override fun drawUntilSatisfaction(
        generateCard: () -> Card,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawCard { generateCard() }
        printCards(gameInfo)
        when (pickingState) {
            PickingState.HIT -> drawUntilSatisfaction(generateCard, printCards)
            PickingState.STAND -> return
        }
    }

    fun initializeCards(generateCard: () -> Card) {
        repeat(INITIAL_DRAW_COUNT) {
            gameInfo.addCard(generateCard())
        }
    }

    private fun checkBurst(): PickingState {
        return if (gameInfo.total > MAXIMUM_CARD_TOTAL) PickingState.STAND else PickingState.HIT
    }

    companion object {
        private const val MAXIMUM_CARD_TOTAL = 21
        private const val HIT = "y"
        private const val STAY = "n"
        private const val EXCEPTION_PLAYER_INPUT = "y나 n을 입력해야 합니다."
        private const val INITIAL_DRAW_COUNT = 2
    }
}
