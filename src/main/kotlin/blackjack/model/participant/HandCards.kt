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
        return _cards.fold(
            _cards.sumOf { it.getScore() },
        ) { total, card ->
            if (!card.isAce()) return@fold total
            if (total + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) return total
            total + Denomination.ADDITIONAL_ACE_SCORE
        }
    }
}
