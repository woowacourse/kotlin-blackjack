package model

class Dealer(private val hand: Hand) : Participant(hand) {
    init {
        require(hand.getCardsCount() == 2) { DEALER_INITIAL_CARD_ERROR_MESSAGE }
    }

    override fun performTurn(cardDistributor: CardDistributor): Boolean {
        if (decideToHit()) {
            val drawnCard = cardDistributor.drawCard()
            addCard(drawnCard)
            return true
        }
        return false
    }

    fun getDrawCount(cardDistributor: CardDistributor): Int {
        var drawCount = 0
        while (performTurn(cardDistributor)) {
            drawCount++
        }
        return drawCount
    }

    override fun decideToHit(): Boolean = getScore() <= 16

    companion object {
        private const val DEALER_INITIAL_CARD_ERROR_MESSAGE = "[ERROR] 딜러는 2장의 카드를 가져야 합니다."
    }
}
