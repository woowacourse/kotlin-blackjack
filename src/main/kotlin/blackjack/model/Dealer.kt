package blackjack.model

import blackjack.base.BaseHolder

class Dealer(): BaseHolder() {
    fun drawUntilOverThreshold(gameDeck: GameDeck): Int {
        var drawCount = 0
        while (state is Progressing) {
            if((state as Running).hand.calculate() > THRESHOLD) {
                changeState((state as Running).hitOrStay(false))
            } else {
                changeState((state as Running).hitOrStay(true))
                getCard(gameDeck)
                drawCount++
            }
        }
        return drawCount
    }

    companion object {
        const val THRESHOLD = 16
    }
}
