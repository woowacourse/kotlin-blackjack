package entity

class Dealer(val cards: Cards = Cards()) : User {
    override fun isDistributable(): Boolean = cards.sumOfNumbers() <= MAXIMUM_CARD_SUM_NUMBER

    companion object {
        const val MAXIMUM_CARD_SUM_NUMBER = 16
    }
}
