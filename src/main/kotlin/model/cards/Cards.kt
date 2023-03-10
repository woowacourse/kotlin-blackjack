package model.cards

import java.util.LinkedList

data class Cards(private val cards: LinkedList<Card>) {
    val size
        get() = cards.size
    constructor(cards: List<Card>) : this(LinkedList(cards))

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun toList(): List<Card> = cards.toList()

    fun add(card: Card) {
        require(!cards.toList().contains(card)) { CARD_DUPLICATE_ERROR }
        cards.add(card)
    }

    fun pop(): Card {
        require(cards.toList().isNotEmpty()) { OUT_OF_INDEX_CARDS_CURSOR }
        return cards.pop()
    }

    fun sum(): Int {
        var sum = filterSum { it != Rank.ACE }
        sum = filterSum(sum) { it == Rank.ACE }
        return sum
    }

    private fun filterSum(score: Int = 0, condition: (Rank) -> Boolean): Int {
        var sum = score
        cards.toList().filter { condition(it.rank) }.forEach {
            sum += it.rank.getScore(sum)
        }
        return sum
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
