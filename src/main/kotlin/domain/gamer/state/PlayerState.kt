package domain.gamer.state

import domain.card.Card

class PlayerState(private val _cards: MutableList<Card> = mutableListOf()) : ParticipantState(_cards) {

    fun checkBurst(): Boolean = calculateCardSum() > CARD_SUM_MAX_VALUE

    companion object {
        private const val CARD_SUM_MAX_VALUE = 21
    }
}
