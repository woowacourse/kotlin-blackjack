package domain.gamer

import domain.card.Card

class Dealer(private val _cards: MutableList<Card> = mutableListOf()) : Participant(_cards) {
    fun checkAvailableForPick() = _cards.sumOf { it.cardValue.value } > CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
