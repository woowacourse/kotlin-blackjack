package domain

class Dealer : Participant(Name(DEALER_NAME)) {
    override fun isPossibleDrawCard(): Boolean = curScore() <= DEALER_ADD_CARD_CONDITION

    fun getProfit(participant: Participant): Double {
        return participant.cardsState.resultProfit(this.cardsState)
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_ADD_CARD_CONDITION = 16
    }
}
