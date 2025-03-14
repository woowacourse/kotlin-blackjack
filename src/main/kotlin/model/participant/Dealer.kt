package model.participant

import model.card.Card
import model.card.Cards

class Dealer(dealerCards: Cards) : Participant(dealerCards) {
    override fun turn(drawnCard: Card): Boolean {
        if (canHit()) {
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun drawCount(drawCard: () -> Card): Int {
        var drawCount = DEFAULT_DRAW_COUNT
        drawCount += addDraws(drawCard)
        return drawCount
    }

    private fun addDraws(drawCard: () -> Card): Int {
        var count = DEFAULT_DRAW_COUNT
        while (turn(drawCard())) {
            count++
        }
        return count
    }

    override fun canHit(): Boolean = currentScore <= DEALER_HIT_LIMIT

    companion object {
        private const val DEFAULT_DRAW_COUNT = 0
        private const val DEALER_HIT_LIMIT = 16
    }
}
