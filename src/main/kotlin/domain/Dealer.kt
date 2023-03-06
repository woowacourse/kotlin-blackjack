package domain

class Dealer(cards: Cards) : Participant(Name(DEALER_NAME), cards) {
    override fun showInitCards(): List<Card> {
        return cards.list.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean = getScore().getValue() <= DEALER_ADD_CARD_CONDITION

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val TAKE_ONE = 1
    }
}
