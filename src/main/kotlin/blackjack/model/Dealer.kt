package blackjack.model

import blackjack.base.BaseHolder

class Dealer(override val humanName: HumanName = HumanName(DEFAULT_DEALER_NAME)) : BaseHolder() {
    fun drawDealerCard(
        gameDeck: GameDeck,
        alert: () -> Unit,
    ) {
        while (hand.state == UserState.RUNNING) {
            if (hand.calculate() <= THRESHOLD) {
                alert()
                takeCard(gameDeck.drawCard())
            } else {
                hand.changeState(UserState.STAY)
            }
        }
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val THRESHOLD = 16
    }
}
