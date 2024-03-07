package blackjack.model

import blackjack.base.BaseHolder
import blackjack.model.UserState.RUNNING
import blackjack.model.UserState.STAY

class Dealer(override val humanName: HumanName = HumanName(DEFAULT_DEALER_NAME)) : BaseHolder() {
    fun drawDealerCard(
        card: Card,
        printDealerDrawCard: () -> Unit,
    ) {
        while (hand.state == RUNNING) {
            if (shouldDrawCard()) {
                drawCardAndPrint(card = card, printDealerDrawCard = printDealerDrawCard)
            } else {
                hand.changeState(userState = STAY)
            }
        }
    }

    private fun shouldDrawCard(): Boolean = hand.calculate() <= THRESHOLD

    private fun drawCardAndPrint(
        card: Card,
        printDealerDrawCard: () -> Unit,
    ) {
        printDealerDrawCard()
        takeCard(card = card)
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val THRESHOLD = 16
    }
}
