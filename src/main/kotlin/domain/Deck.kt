package domain

import domain.card.Card

class Deck(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    init {
        require(_cards.size != EMPTY) { ERROR_EMPTY_DECK }
    }

    fun addCard(card: List<Card>) {
        _cards.add(card.first())
    }

    companion object {
        private const val ERROR_EMPTY_DECK = "[ERROR] 아무런 카드가 없습니다."
        private const val EMPTY = 0
    }
}
