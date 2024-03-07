package blackjack.model

class ScoreBoard {
    val handCard = HandCard()
    var cardSum = 0
        private set

    fun add(card: Card) {
        handCard.add(card)
        cardSum += card.denomination.score
    }

    fun optimizeCardSum(burstCondition: Int) {
        val aceCount = handCard.getAceCount()
        repeat(aceCount) {
            if (handleAceValue(burstCondition)) return@repeat
        }
    }

    private fun handleAceValue(burstCondition: Int): Boolean {
        if (cardSum + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) {
            return true
        }
        cardSum += Denomination.ADDITIONAL_ACE_SCORE
        return false
    }
}
