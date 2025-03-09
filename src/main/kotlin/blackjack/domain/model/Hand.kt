package blackjack.domain.model

class Hand() {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    constructor(cards: List<Card>) : this() {
        add(cards)
    }

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun computePoint(): Int {
        val point = _cards.sumOf { it.rank.point }
        return point + computeBonusPoint(point)
    }

    private fun computeBonusPoint(point: Int): Int {
        if (point + BONUS_POINT <= BUST_THRESHOLD && hasAce()) return BONUS_POINT
        return 0
    }

    private fun hasAce(): Boolean = _cards.any { it.rank == Rank.ACE }

    fun isBusted(): Boolean {
        return computePoint() > BUST_THRESHOLD
    }

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val BONUS_POINT = 10
    }
}
