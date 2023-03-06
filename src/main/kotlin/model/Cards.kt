package model

class Cards(cards: Set<Card>) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()
    val size: Int
        get() = _cards.size

    fun add(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.sumOf { it.rank.score }
        repeat(_cards.filter { it.rank == Rank.ACE }.size) {
            if (sum + ACE_BONUS_TEN <= PARTICIPANT_STANDARD_BUST_POINT)
                sum += ACE_BONUS_TEN
        }
        return sum
    }

    companion object {
        const val DEALER_STANDARD_HIT_POINT = 16
        const val PARTICIPANT_STANDARD_BUST_POINT = 21
        private const val ACE_BONUS_TEN = 10
    }
}
