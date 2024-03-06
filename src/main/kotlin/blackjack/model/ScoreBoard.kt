package blackjack.model

class ScoreBoard {
    val handCard = HandCard()
    var cardSum = 0
        private set

    fun add(card: Card) {
        handCard.add(card)
        cardSum += card.getValue()
    }

    fun getOptimizeCardSum(burstCondition: Int): Int {
        val aceCount = handCard.getAceCount()
        repeat(aceCount) {
            if (handleAceValue(burstCondition)) return@repeat
        }
        return cardSum
    }

    private fun handleAceValue(burstCondition: Int): Boolean {
        if (cardSum + Card.ADDITIONAL_ACE_VALUE > burstCondition) {
            return true
        }
        cardSum += Card.ADDITIONAL_ACE_VALUE
        return false
    }
}
