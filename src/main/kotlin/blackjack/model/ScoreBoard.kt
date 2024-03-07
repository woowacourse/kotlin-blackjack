package blackjack.model

class ScoreBoard {
    val handCards = HandCards()
    var cardSum = 0
        private set

    fun add(card: Card) {
        handCards.add(card)
    }

    fun optimizeCardSum(burstCondition: Int) {
        cardSum = 0
        handCards.cards.forEach {
            cardSum += it.denomination.score
        }
        repeat(handCards.getAceCount()) {
            if (cardSum + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) return
            cardSum += Denomination.ADDITIONAL_ACE_SCORE
        }
    }
}
