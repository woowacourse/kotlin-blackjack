package blackjack.model

import blackjack.base.BaseHolder
import blackjack.model.state.Progressing

class Dealer(): BaseHolder() {
    fun drawUntilOverThreshold(gameDeck: GameDeck): Int {
        var drawCount = 0
        while (state is Progressing) {
            if(state.hand.calculate() > THRESHOLD) {
                changeState(state.hitOrStay(false))
            } else {
                changeState(state.hitOrStay(true))
                takeCard(gameDeck.drawCard())
                drawCount++
            }
        }
        return drawCount
    }

    companion object {
        const val THRESHOLD = 16
    }
}
