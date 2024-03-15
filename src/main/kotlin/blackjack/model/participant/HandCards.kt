package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Denomination

class HandCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()
    val size: Int
        get() = _cards.size

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getCardSum(burstCondition: Int): Int {
        val total = _cards.sumOf { it.getScore() }
        if (canAddAceBonusScore(total, burstCondition)) {
            return total + Denomination.ACE_BONUS_SCORE
        }
        return total
    }

    private fun canAddAceBonusScore(
        total: Int,
        burstCondition: Int,
    ): Boolean {
        return _cards.any { it.isAce() } && total + Denomination.ACE_BONUS_SCORE <= burstCondition
    }
}
