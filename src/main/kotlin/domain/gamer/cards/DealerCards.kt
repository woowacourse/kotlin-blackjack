package domain.gamer.cards

import domain.card.Card

class DealerCards(cards: MutableList<Card> = mutableListOf()) : ParticipantCards(cards) {
    override fun checkOverCondition(): Boolean = calculateCardSum() > CARD_PICK_CONDITION

    companion object {
        private const val CARD_PICK_CONDITION = 16
    }
}
