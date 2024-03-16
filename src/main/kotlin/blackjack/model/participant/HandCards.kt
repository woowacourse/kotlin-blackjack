package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Denomination

class HandCards(private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(burstCondition: Int): Int {
        val score = _cards.sumOf { it.score() }
        if (addableAceBonusScore(score, burstCondition)) {
            return score + Denomination.ACE_BONUS_SCORE
        }
        return score
    }

    private fun addableAceBonusScore(
        total: Int,
        burstCondition: Int,
    ): Boolean {
        return _cards.any { it.isAce() } && total + Denomination.ACE_BONUS_SCORE <= burstCondition
    }
}
