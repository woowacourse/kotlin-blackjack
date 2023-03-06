package domain.gamer

import domain.gamer.cards.Cards

class Dealer(override val cards: Cards) : Participant(cards) {
    fun checkAvailableForPick() = calculateCardSum() <= CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
