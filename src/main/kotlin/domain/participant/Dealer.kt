package domain.participant

import domain.card.Card

class Dealer() : Participant(Name(DEALER_NAME), BettingMoney(0)) {
    override fun showInitCards(): List<Card> {
        return state.cards().list.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean = state.cards().getScore().getValue() <= DEALER_ADD_CARD_CONDITION

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val TAKE_ONE = 1
    }
}
