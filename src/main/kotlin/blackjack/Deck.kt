package blackjack

import java.lang.IllegalStateException

class Deck(private val deckOrder: List<Int>) {
    private var index = 0
    init {
        require(deckOrder.size == 52) { "덱의 사이즈는 52여야 합니다" }
        require(deckOrder.distinct().size == 52) { "카드는 중복될 수 없습니다" }
        require(deckOrder.filter { it in 0..51 }.size == 52) { "카드 번호는 0부터 51까지 입니다" }
    }

    fun getCard() : Card {
        if (index > 51) throw IllegalStateException()
        val card = Card.matchCard(deckOrder[index])
        index++
        return card
    }
}
