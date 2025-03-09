package model

class Dealer(dealerCards: Cards) : Participant(dealerCards) {
    init {
        require(dealerCards.totalCount() == 2) { DEALER_INITIAL_CARD_ERROR_MESSAGE }
    }

    override fun turn(drawnCard: Card): Boolean {
        if (canHit()) {
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun drawCount(drawnCard: Card): Int {
        var drawCount = DEFAULT_DRAW_COUNT
        while (canHit()) {
            if (turn(drawnCard)) drawCount++
        }
        return drawCount
    }

    override fun canHit(): Boolean = currentScore() <= DEALER_HIT_LIMIT

    companion object {
        private const val DEFAULT_DRAW_COUNT = 0
        private const val DEALER_INITIAL_CARD_ERROR_MESSAGE = "[ERROR] 딜러는 2장의 카드를 가져야합니다."
        private const val DEALER_HIT_LIMIT = 16
    }
}
