package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean = resultSum() <= DEALER_ADD_CARD_CONDITION

    companion object {
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val TAKE_ONE = 1
    }
}
