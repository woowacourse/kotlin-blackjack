package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.judge.Referee

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
        if (countAce() >= ACE_COUNT_VALUE_CHANGE_CONDITION) {
            CardValue.ACE.aceValue
        } else if (value > Referee.CARD_SUM_MAX_VALUE - CardValue.ACE.value) {
            CardValue.ACE.aceValue
        } else {
            CardValue.ACE.value
        }

    private fun countAce() = _cards.count { it.cardValue.title == "ACE" }

    companion object {
        private const val ACE_COUNT_VALUE_CHANGE_CONDITION = 2
    }
}
