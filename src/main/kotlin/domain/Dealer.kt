package domain

import state.RateOfProfit

class Dealer : Participant(Name(DEALER_NAME)) {
    override fun isPossibleDrawCard(): Boolean = getScore().value <= DEALER_ADD_CARD_CONDITION

    fun getProfit(participant: Participant): RateOfProfit {
        return participant.cardsState.resultProfit(this.cardsState)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        const val DEALER_ADD_CARD_CONDITION = 16
    }
}
