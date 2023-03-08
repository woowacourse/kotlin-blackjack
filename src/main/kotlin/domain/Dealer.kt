package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun isPossibleDrawCard(): Boolean = resultSum() <= DEALER_ADD_CARD_CONDITION

    companion object {
        const val DEALER_ADD_CARD_CONDITION = 16
    }
}
