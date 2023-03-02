package domain.deck

import domain.card.Card

class Deck(private val deck: List<Card>) {
    private val _deck = deck.toMutableList()

    fun giveCard() = _deck.removeLastOrNull() ?: check(false) { "[ERROR] 카드덱을 모두 사용했습니다." }
}
