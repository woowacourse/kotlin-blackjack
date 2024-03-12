package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Dealer(val nickname: Nickname = Nickname(DEFAULT_DEALER_NAME)) : CardHolder() {
    fun drawDealerCard(printDealerDrawCard: () -> Unit) {
        while (state is Hit) {
            drawDecision(printDealerDrawCard)
        }
    }

    private fun drawDecision(printDealerDrawCard: () -> Unit) {
        if (shouldDrawCard()) {
            drawCardAndPrint(printDealerDrawCard = printDealerDrawCard)
        } else {
            changeState(state = Stay)
        }
    }

    private fun shouldDrawCard(): Boolean = hand.calculate() <= DEALER_CARD_DRAW_THRESHOLD

    private fun drawCardAndPrint(printDealerDrawCard: () -> Unit) {
        printDealerDrawCard()
        addCard(card = GameDeck.drawCard())
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_CARD_DRAW_THRESHOLD = 16
    }
}
