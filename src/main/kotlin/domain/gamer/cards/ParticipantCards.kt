package domain.gamer.cards

import domain.card.Card
import domain.card.CardValue
import domain.judge.Referee

abstract class ParticipantCards(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()
    open fun pickCard(card: Card) {
        _cards.add(card)
    }

    fun calculateCardSum(): Int {
        val value = _cards.sumOf { it.cardValue.value }
        return value + checkAceValue(value)
    }

    abstract fun checkOverCondition(): Boolean

    private fun checkAceValue(value: Int): Int {
        if (cards.any { it.cardValue == CardValue.ACE } && value <= ANOTHER_ACE_VALUE)
            return Referee.CARD_SUM_MAX_VALUE - ANOTHER_ACE_VALUE
        return ZERO
    }

    companion object {
        private const val ANOTHER_ACE_VALUE = 11
        private const val ZERO = 0
    }
}
