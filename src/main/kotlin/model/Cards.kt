package model

import java.util.LinkedList

data class Cards(val cards: LinkedList<Card>) {
    constructor(cards: List<Card>) : this(LinkedList(cards))

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
