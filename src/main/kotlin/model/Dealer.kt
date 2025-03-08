package model

class Dealer(dealerCards: Cards) : Participant(dealerCards) {
    init {
        require(dealerCards.totalCount() == 2) { DEALER_INITIAL_CARD_ERROR_MESSAGE }
    }

    override fun turn(cards: Cards): Boolean {
        if (canHit()) {
            val drawnCard = drawCard(cards.allCards)
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun drawCount(allCards: Cards): Int {
        var drawCount = 0
        while (canHit()) {
            if (turn(allCards)) drawCount++
        }
        return drawCount
    }

    override fun canHit(): Boolean = currentScore() <= 16

    companion object {
        private const val DEALER_INITIAL_CARD_ERROR_MESSAGE = "[ERROR] 딜러는 2장의 카드를 가져야합니다."
    }
}
