package blackjack.domain.card

import java.util.Objects

class Cards(_cards: List<Card> = listOf()) {

    private val _cards: MutableList<Card> = _cards.toMutableList()
    val values: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun calculateScore(): Int {
        if (checkHavingAce()) return calculateHavingAceScore()
        return _cards.sumOf { it.number.value }
    }

    fun isBlackjack(): Boolean = ((_cards.size == CARD_SETTING_COUNT) and (calculateScore() == BUST_CRITERIA))

    fun isBust(): Boolean = (calculateScore() > BUST_CRITERIA)

    fun getSize(): Int = _cards.size

    private fun checkHavingAce(): Boolean = _cards.any { it.isAce() }

    private fun calculateHavingAceScore(): Int {
        var result = _cards.sumOf { it.number.value }
        if ((BUST_CRITERIA - result) >= ACE_GAP) result += ACE_GAP
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Cards) return false
        return this.values == other.values
    }

    override fun hashCode(): Int = Objects.hash(_cards)

    companion object {
        const val BUST_CRITERIA = 21
        const val ACE_GAP = 10
        const val CARD_SETTING_COUNT = 2
    }
}
