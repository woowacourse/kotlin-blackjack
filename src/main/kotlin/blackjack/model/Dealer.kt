package blackjack.model

import blackjack.base.BaseHolder

class Dealer : BaseHolder() {
    fun drawDealerCard(
        gameDeck: GameDeck,
    ): Int {
        var drawCount = 0
        while (status.state == UserState.RUNNING) {
            if (status.hand.calculate() <= THRESHOLD) {
                takeCard(gameDeck.drawCard())
                drawCount++
            } else {
                status.changeState(UserState.STAY)
            }
        }
        return drawCount
    }

    companion object {
        const val THRESHOLD = 16
    }
}
