package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch private constructor(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card> get() = _cards.toList()

    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalScore(): Int {
        var result = cards.sumOf { it.cardNumber.value }
        if (result > MAX_SCORE_CONDITION) return result
        if (containsAce()) {
            result = getOptimizedResult(result)
        }
        return result
    }

    private fun getOptimizedResult(result: Int): Int {
        var optimizedResult = result
        if ((optimizedResult + ACE_SCORE_GAP) <= MAX_SCORE_CONDITION) optimizedResult += ACE_SCORE_GAP
        return optimizedResult
    }

    fun isBurst(): Boolean = getTotalScore() > MAX_SCORE_CONDITION

    fun isBlackJack(): Boolean = cards.size == 2 && getTotalScore() == 21

    private fun containsAce(): Boolean = cards.map { card -> card.isAce() }.reduce { acc, isAce -> acc && isAce }

    companion object {
        private const val ACE_SCORE_GAP = 10
    }
}
