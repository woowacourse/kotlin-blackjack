package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Denomination

class HandCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getCardSum(burstCondition: Int): Int {
        var cardSum = _cards.sumOf { it.getScore() }
        cardSum =
            _cards.fold(cardSum) { total, card ->
                if (!card.isAce()) return@fold total
                if (total + Denomination.ADDITIONAL_ACE_SCORE > burstCondition) return total
                total + Denomination.ADDITIONAL_ACE_SCORE
            }
        return cardSum
    }
}
