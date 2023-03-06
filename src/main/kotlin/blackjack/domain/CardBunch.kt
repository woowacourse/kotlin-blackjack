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
        val aceCount = cards.count { it.cardNumber == CardNumber.ACE }
        var result = cards.sumOf { it.cardNumber.value }
        if (result > MAX_SCORE_CONDITION) return result
        repeat(aceCount) {
            if ((result + ACE_SCORE_GAP) > MAX_SCORE_CONDITION) return result
            result += ACE_SCORE_GAP
        }
        return result
    }

    fun isBurst(): Boolean = getTotalScore() > MAX_SCORE_CONDITION

    companion object {
        private const val DUPLICATE_ERROR = "중복된 카드는 추가할 수 없습니다."
        private const val ACE_SCORE_GAP = 10
    }
}
