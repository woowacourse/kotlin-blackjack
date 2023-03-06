package model

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()
    val size: Int
        get() = _cards.size

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun add(card: Card) {
        require(!_cards.contains(card)) { CARD_DUPLICATE_ERROR }
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = filterSum { it != Rank.ACE }
        sum += filterSum { it == Rank.ACE }
        return sum
    }

    private fun filterSum(condition: (Rank) -> Boolean): Int {
        var sum = 0
        _cards.filter { condition(it.rank) }.forEach {
            sum += it.rank.getScore(sum)
        }
        return sum
    }

    fun pop(): Card {
        require(_cards.isNotEmpty()) { OUT_OF_INDEX_CARDS_CURSOR }
        val card = _cards[0]
        _cards.removeAt(0)
        return card
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
