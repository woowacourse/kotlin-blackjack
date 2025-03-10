package model

class Dealer(dealerCards: Cards) : Participant(dealerCards) {
    init {
        require(dealerCards.getCardsCount() == 2) { DEALER_INITIAL_CARD_ERROR_MESSAGE }
    }

    override fun performTurn(cards: Cards): Boolean {
        if (decideToHit()) {
            val drawnCard = drawCard(cards.allCards)
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun getDrawCount(allCards: Cards): Int {
        var drawCount = 0
        while (decideToHit()) {
            if (performTurn(allCards)) drawCount++
        }
        return drawCount
    }

    override fun decideToHit(): Boolean = getScore() <= 16

    companion object {
        private const val DEALER_INITIAL_CARD_ERROR_MESSAGE = "[ERROR] 딜러는 2장의 카드를 가져야합니다."
    }
}
