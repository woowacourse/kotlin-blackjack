package blackjack.model

import blackjack.base.BaseHolder

class Dealer : BaseHolder() {
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
        const val THRESHOLD = 16
    }
}
