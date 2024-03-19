package blackjack.model.participants

import blackjack.model.card.Card
import blackjack.model.gameInfo.GameInfo
import blackjack.model.gameInfo.PickingState

abstract class Participant {
    abstract fun drawCardsUntilStand(
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    )

    fun determineContinuation(
        pickingState: PickingState,
        generateCard: () -> Card?,
        printCards: (GameInfo) -> Unit,
    ) {
        when (pickingState) {
            PickingState.HIT -> drawCardsUntilStand(generateCard, printCards)
            PickingState.STAND -> return
        }
    }

    abstract fun drawSingleCard(generateCard: () -> Card?): PickingState
}
