package blackjack.model.participants

import blackjack.model.card.Card
import blackjack.model.gameInfo.GameInfo
import blackjack.model.gameInfo.PickingState

class Player(
    val gameInfo: GameInfo,
    val onInputDecision: () -> String,
) : Participant() {
    init {
        require(gameInfo.name.isNotEmpty() && gameInfo.name.isNotBlank()) { EXCEPTION_PLAYER_NAME }
    }

    override fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    ) {
        val pickingState = drawSingleCard(generateCard)
        printCards(gameInfo)
        determineContinuation(pickingState, generateCard, printCards)
    }

    override fun drawSingleCard(generateCard: () -> Card?): PickingState {
        val inputDecision = onInputDecision()
        val pickingState =
            PickingState.entries.find { it.value == inputDecision }
                ?: throw IllegalStateException(EXCEPTION_PLAYER_INPUT)

        return when (pickingState) {
            PickingState.HIT -> {
                gameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))
                checkBurst()
            }
            PickingState.STAND -> PickingState.STAND
        }
    }

    private fun checkBurst(): PickingState {
        if (gameInfo.sumOfCards >= MAXIMUM_CARD_TOTAL) {
            return PickingState.STAND
        }
        return PickingState.HIT
    }

    companion object {
        private const val MAXIMUM_CARD_TOTAL = 21
        private const val EXCEPTION_PLAYER_NAME = "플레이어의 이름은 0자 혹은 공백이 될 수 없습니다."
        private const val EXCEPTION_PLAYER_INPUT = "y나 n을 입력해야 합니다."
        private const val INITIAL_DRAW_COUNT = 2
        private const val EXCEPTION_NO_CARDS_LEFT = "카드가 모두 소진되었습니다."

        fun of(
            gameInfo: GameInfo,
            onInputDecision: () -> String,
            generateCard: () -> Card?,
        ): Player {
            repeat(INITIAL_DRAW_COUNT) {
                gameInfo.addCard(generateCard() ?: throw IllegalStateException(EXCEPTION_NO_CARDS_LEFT))
            }

            return Player(gameInfo, onInputDecision)
        }
    }
}
