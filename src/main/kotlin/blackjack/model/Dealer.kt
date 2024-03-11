package blackjack.model

import blackjack.state.State.Finished.Stay
import blackjack.state.State.Running.Hit

class Dealer(val nickname: Nickname = Nickname(DEFAULT_DEALER_NAME)) : CardHolder() {
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

    private fun shouldDrawCard(): Boolean = hand.calculate() <= DEALER_CARD_DRAW_THRESHOLD

    private fun drawCardAndPrint(
        card: Card,
        printDealerDrawCard: () -> Unit,
    ) {
        printDealerDrawCard()
        takeCard(card = card)
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_CARD_DRAW_THRESHOLD = 16
    }
}
