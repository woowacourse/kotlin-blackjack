package domain.gamer

import domain.card.Card

abstract class Participant(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()
    open fun pickCard(card: Card) {
        _cards.add(card)
    }
    fun calculateCardSum() = _cards.sumOf { it.cardValue.value }
}
