package model

import java.util.LinkedList

class CardPack(private val cards: LinkedList<Card>) {
    val size
        get() = cards.size

    constructor(cards: List<Card>) : this(LinkedList(cards))

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun pop(): Card {
        require(cards.isNotEmpty()) { OUT_OF_INDEX_CARDS_CURSOR }
        return cards.pop()
    }

    fun shuffled() = CardPack(cards.shuffled())

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
