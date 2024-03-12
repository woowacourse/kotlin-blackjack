package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Denomination

class HandCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCards(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getCardSum(burstCondition: Int): Int {
        var cardSum = 0
        _cards.forEach {
            cardSum += it.getScore()
        }
        repeat(getAceCount()) {
            if (cardSum + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) return cardSum
            cardSum += Denomination.ADDITIONAL_ACE_SCORE
        }
        return cardSum
    }

    private fun getAceCount(): Int {
        return _cards.count { it.isAce() }
    }
}
