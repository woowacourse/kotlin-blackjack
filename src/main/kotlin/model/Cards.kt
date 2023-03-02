package model

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun add(card: Card) {
        require(!cards.contains(card)) { CARD_DUPLICATE_ERROR }
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.filter { it.rank != Rank.ACE }.sumOf { it.rank.getScore(0) }
        for (card in _cards.filter { it.rank == Rank.ACE }) {
            sum += card.rank.getScore(sum)
        }
        return sum
    }

    override fun toString(): String = cards.joinToString(", ")

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
