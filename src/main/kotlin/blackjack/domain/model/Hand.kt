package blackjack.domain.model

class Hand() {
    private val cards: MutableList<Card> = mutableListOf()

    constructor(cards: List<Card>) : this() {
        add(cards)
    }

    fun show(count: Int = cards.size): List<Card> {
        return cards.take(count).map { card -> card.copy() }
    }

    fun add(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun computePoint(): Int {
        val point = cards.sumOf { it.rank.point }
        return point + computeBonusPoint(point)
    }

    private fun computeBonusPoint(point: Int): Int {
        if (point + BONUS_POINT <= BUST_THRESHOLD && hasAce()) return BONUS_POINT
        return 0
    }

    private fun hasAce(): Boolean = cards.any { it.rank == Rank.ACE }

    fun isBusted(): Boolean {
        return computePoint() > BUST_THRESHOLD
    }

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val BONUS_POINT = 10
    }
}
