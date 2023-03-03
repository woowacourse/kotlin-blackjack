package domain.gamer.state

import domain.card.Card

class DealerState(private val _cards: MutableList<Card> = mutableListOf()) : ParticipantState(_cards) {
    fun checkAvailableForPick() = calculateCardSum() <= CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
