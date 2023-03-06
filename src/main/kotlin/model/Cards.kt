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
            if (sum + ACE_BONUS_TEN <= 21)
                sum += ACE_BONUS_TEN
        }
        return sum
    }

    companion object {
        private const val ACE_BONUS_TEN = 10
    }
}
