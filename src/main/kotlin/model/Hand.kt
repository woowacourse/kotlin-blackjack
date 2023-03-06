package model

class Hand(private val cards: Cards) {
    val size
        get() = cards.cards.size
    constructor(cards: List<Card>) : this(Cards(cards))

    fun toList() = cards.cards.toList()

    fun add(card: Card) {
        require(!cards.cards.contains(card)) { CARD_DUPLICATE_ERROR }
        cards.cards.add(card)
    }

    fun sum(): Int {
        var sum = filterSum { it != Rank.ACE }
        sum = filterSum(sum) { it == Rank.ACE }
        return sum
    }

    private fun filterSum(score: Int = 0, condition: (Rank) -> Boolean): Int {
        var sum = score
        cards.cards.filter { condition(it.rank) }.forEach {
            sum += it.rank.getScore(sum)
        }
        return sum
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
