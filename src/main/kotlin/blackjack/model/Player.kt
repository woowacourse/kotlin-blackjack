package blackjack.model

class Player(
    val gameInfo: GameInfo,
    val onInputDecision: () -> String,
) : Participant {
    override fun drawCard(generateCard: () -> Card): PickingState {
        val pickingState =
            PickingState.entries.find { it.value == onInputDecision() }
                ?: throw IllegalArgumentException(EXCEPTION_PLAYER_INPUT)
        return when (pickingState) {
            PickingState.HIT -> {
                gameInfo.addCard(generateCard())
                checkBurst()
            }
            PickingState.STAND -> PickingState.STAND
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
        if (gameInfo.sumCardValues() > MAXIMUM_CARD_TOTAL) {
            return PickingState.STAND
        }
        return PickingState.HIT
    }

    companion object {
        private const val MAXIMUM_CARD_TOTAL = 21
        private const val EXCEPTION_PLAYER_INPUT = "y나 n을 입력해야 합니다."
        private const val INITIAL_DRAW_COUNT = 2
    }
}
