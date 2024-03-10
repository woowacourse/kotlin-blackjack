package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Denomination

class ScoreBoard {
    val handCards = HandCards()
    var cardSum = 0
        private set

    fun addCardAndUpdateCardSum(
        card: Card,
        burstCondition: Int,
    ) {
        handCards.addCard(card)
        updateCardSum(burstCondition)
    }

    private fun updateCardSum(burstCondition: Int) {
        cardSum = 0
        handCards.cards.forEach {
            addCardSum(it.denomination.score)
        }
        repeat(handCards.getAceCount()) {
            if (cardSum + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) return
            addCardSum(Denomination.ADDITIONAL_ACE_SCORE)
        }
    }

    private fun addCardSum(score: Int) {
        cardSum += score
    }
}
