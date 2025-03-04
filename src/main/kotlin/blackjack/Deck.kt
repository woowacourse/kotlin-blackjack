package blackjack

import java.lang.IllegalStateException

class Deck(private val deckOrder: List<Int>) {
    private var index = 0
    init {
        require(deckOrder.size == MAXIMUM_DECK_SIZE) { "덱의 사이즈는 52여야 합니다" }
        require(deckOrder.distinct().size == MAXIMUM_DECK_SIZE) { "카드는 중복될 수 없습니다" }
        require(deckOrder.filter { it in 0 until MAXIMUM_DECK_SIZE }.size == MAXIMUM_DECK_SIZE) { "카드 번호는 0부터 51까지 입니다" }
    }

    fun getCard() : Card {
        if (index >= MAXIMUM_DECK_SIZE) throw IllegalStateException()
        val card = Card.matchCard(deckOrder[index])
        index++
        return card
    }

    companion object {
        const val MAXIMUM_DECK_SIZE = 52
    }
}
