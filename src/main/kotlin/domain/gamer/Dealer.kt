package domain.gamer

import domain.card.Card

class Dealer(private val _card: MutableList<Card> = mutableListOf()) : Participant(_card) {
    fun checkAvailableForPick() = _card.sumOf { it.cardValue.value } > CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
