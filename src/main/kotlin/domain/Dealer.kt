package domain

import domain.card.Cards

class Dealer(cards: Cards) : Participant(Name(DEALER_NAME), cards) {
    override fun isPossibleDrawCard(): Boolean = resultSum() <= DEALER_ADD_CARD_CONDITION

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_ADD_CARD_CONDITION = 16
    }
}
