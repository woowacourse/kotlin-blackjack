package domain.gamer.state

import domain.card.Card

abstract class ParticipantState(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()
    open fun pickCard(card: Card) {
        _cards.add(card)
    }

    fun calculateCardSum() = _cards.sumOf { it.cardValue.value }
}
