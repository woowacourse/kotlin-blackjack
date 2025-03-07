package model

class Dealer(dealerCards: Cards) : Participant(dealerCards) {
    init {
        require(dealerCards.getCardsCount() == 2) { "[ERROR] 딜러는 2장의 카드를 가져야합니다." }
    }

    override fun turn(cards: Cards): Boolean {
        if (isHit()) {
            val drawnCard = drawCard(cards.allCards)
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun getDrawCount(allCards: Cards): Int {
        var drawCount = 0
        while (isHit()) {
            if (turn(allCards)) drawCount++
        }
        return drawCount
    }

    override fun isHit(): Boolean = getScore() <= 16
}
