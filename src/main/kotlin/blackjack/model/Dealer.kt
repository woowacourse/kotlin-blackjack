package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Dealer(val nickname: Nickname = Nickname(DEFAULT_DEALER_NAME)) : CardHolder() {
    fun drawDealerCard(printDealerDrawCard: () -> Unit) {
        while (state is Hit) {
            if (shouldDrawCard()) {
                drawCardAndPrint(card = GameDeck.drawCard(), printDealerDrawCard = printDealerDrawCard)
            } else {
                changeState(state = Stay)
            }
        }
    }

    private fun shouldDrawCard(): Boolean = hand.calculate() <= DEALER_CARD_DRAW_THRESHOLD

    private fun drawCardAndPrint(
        card: Card,
        printDealerDrawCard: () -> Unit,
    ) {
        printDealerDrawCard()
        addCard(card = card)
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_CARD_DRAW_THRESHOLD = 16
    }
}
