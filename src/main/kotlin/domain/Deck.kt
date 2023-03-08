package domain

import domain.card.Card

class Deck(_cards: List<Card>) {
    private val _cards: MutableList<Card> = _cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(_cards.isNotEmpty()) { ERROR_EMPTY_DECK }
    }

    fun addCard(card: List<Card>) {
        _cards.add(card.first())
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 아무런 카드가 없습니다."
    }
}
