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
        var sum = _cards.filter { it.rank != Rank.ACE }.sumOf { it.rank.getScore() }
        sum += _cards.filter { it.rank == Rank.ACE }.sumOf { it.rank.getScore(sum) }
        return sum
    }
}
