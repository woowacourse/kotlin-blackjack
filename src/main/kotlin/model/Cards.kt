package model

import java.util.LinkedList

open class Cards(cards: List<Card>) {
    protected val cards = LinkedList(cards)

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun toList() = cards.toList()
    fun size() = cards.size

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
