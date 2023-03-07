package domain

class Dealer(val name: String = DEALER_NAME, val cards: Cards) {
    fun isOverSumCondition(): Boolean = (cards.actualCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}
