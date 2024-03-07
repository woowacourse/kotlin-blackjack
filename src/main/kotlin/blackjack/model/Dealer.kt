package blackjack.model

class Dealer(
    val players: Set<String>,
    val gameInfo: GameInfo = GameInfo("딜러"),
) : Participant {
    init {
        require(players.size in PLAYER_NUM_RANGE) {
            EXCEPTION_NUMBER_OF_PLAYERS.format(players.size)
        }
    }

    override fun drawCard(generateCard: () -> Card): PickingState {
        if (isDrawAvailable()) {
            gameInfo.addCard(generateCard())
            return PickingState.CONTINUE
        } else {
            return PickingState.STOP
        }
    }

    override fun drawUntilSatisfaction(
        generateCard: () -> Card,
        printCards: (GameInfo) -> Unit,
    ) {
        while (true) {
            val pickingState = drawCard { generateCard() }
            when (pickingState) {
                PickingState.CONTINUE -> {
                    printCards(gameInfo)
                }
                PickingState.STOP -> break
            }
        }
    }

    private fun isDrawAvailable(): Boolean = gameInfo.total <= MAXIMUM_DRAW_THRESHOLD

    companion object {
        private const val MAXIMUM_DRAW_THRESHOLD = 16
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val MAXIMUM_NUMBER_OF_PLAYERS = 8
        private val PLAYER_NUM_RANGE = MINIMUM_NUMBER_OF_PLAYERS..MAXIMUM_NUMBER_OF_PLAYERS
        private const val EXCEPTION_NUMBER_OF_PLAYERS =
            "플레이어의 수로 %d를 입력했습니다. 플레이어 수는 ${MINIMUM_NUMBER_OF_PLAYERS}부터 ${MAXIMUM_NUMBER_OF_PLAYERS}까지 가능합니다."
    }
}
