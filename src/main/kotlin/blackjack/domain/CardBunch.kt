package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch private constructor(cards: MutableSet<Card>) {
    private val _cards: MutableSet<Card> = cards
    val cards: Set<Card> get() = _cards.toSet()

    constructor(vararg cards: Card) : this(cards.toMutableSet()) {
        validateSize()
    }

    private fun validateSize() {
        require(_cards.size == INITIAL_CARD_SIZE) { INITIAL_SIZE_ERROR }
    }

    fun addCard(card: Card) {
        val originSize = _cards.size
        _cards.add(card)
        require(originSize != _cards.size) { DUPLICATE_ERROR }
    }

    fun getTotalScore(): Int {
        var result = 0
        val sortedCards = cards.sortedBy { it.cardNumber.value }.reversed()

        sortedCards.forEach {
            result += it.cardNumber.value ?: checkAceValue(result)
        }
        return result
    }

    private fun checkAceValue(result: Int): Int {
        return when (result + BIG_ACE_SCORE > MAX_SCORE_CONDITION) {
            true -> SMALL_ACE_SCORE
            false -> BIG_ACE_SCORE
        }
    }

    fun isBurst(): Boolean = getTotalScore() > MAX_SCORE_CONDITION

    companion object {
        private const val INITIAL_SIZE_ERROR = "초기 카드는 2장이 배정되어야 합니다."
        private const val DUPLICATE_ERROR = "중복된 카드는 추가할 수 없습니다."
        private const val INITIAL_CARD_SIZE = 2
        private const val BIG_ACE_SCORE = 11
        private const val SMALL_ACE_SCORE = 1
    }
}
