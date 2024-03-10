package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Dealer(override val humanName: HumanName = HumanName(DEFAULT_DEALER_NAME)) : BaseHolder() {
    fun drawDealerCard(
        gameDeck: GameDeck,
        printDealerDrawCard: () -> Unit,
    ): GameDeck {
        while (state is Hit) {
            if (shouldDrawCard()) {
                drawCardAndPrint(card = gameDeck.drawCard(), printDealerDrawCard = printDealerDrawCard)
            } else {
                changeState(state = Stay)
            }
        }
        return gameDeck
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
