package domain.gamer.state

import domain.card.Card
import domain.card.CardValue

abstract class ParticipantState(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()
    open fun pickCard(card: Card) {
        _cards.add(card)
    }

    fun calculateCardSum(): Int {
        var value = 0
        _cards.forEach {
            value += getCardValue(it, value)
        }
        return value
    }

    private fun getCardValue(card: Card, value: Int) =
        if (card.cardValue == CardValue.ACE) {
            getAceValue(value)
        } else {
            card.cardValue.value
        }

    private fun getAceValue(value: Int) =
        if (countAce() >= 2) {
            CardValue.ACE.aceValue
        } else if (value > 10) {
            CardValue.ACE.aceValue
        } else {
            CardValue.ACE.value
        }

    private fun countAce() = _cards.count { it.cardValue.title == "ACE" }
}
