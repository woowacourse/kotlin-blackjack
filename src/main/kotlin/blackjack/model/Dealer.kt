package blackjack.model

import blackjack.base.BaseHolder
import blackjack.model.UserState.RUNNING
import blackjack.model.UserState.STAY

class Dealer(override val humanName: HumanName = HumanName(DEFAULT_DEALER_NAME)) : BaseHolder() {
    fun drawDealerCard(
        gameDeck: GameDeck,
        printDealerDrawCard: () -> Unit,
    ) {
        while (hand.state == RUNNING) {
            if (hand.calculate() <= THRESHOLD) {
                printDealerDrawCard()
                takeCard(card = gameDeck.drawCard())
            } else {
                hand.changeState(userState = STAY)
            }
        }
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val THRESHOLD = 16
    }
}
