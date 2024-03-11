package blackjack.model

import blackjack.base.BaseHolder

class Dealer : BaseHolder() {
    fun drawDealerCard(
        gameDeck: GameDeck,
        alert: () -> Unit,
    ) {
        while (status.state == UserState.RUNNING) {
            if (status.hand.calculate() <= THRESHOLD) {
                alert()
                takeCard(gameDeck.drawCard())
            } else {
                status.changeState(UserState.STAY)
            }
        }
    }

    companion object {
        const val THRESHOLD = 16
    }
}
