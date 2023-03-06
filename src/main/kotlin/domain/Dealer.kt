package domain

class Dealer(name: String = DEALER_NAME, cards: Cards) : Player(name, cards) {
    fun isOverSumCondition(): Boolean = (cards.actualCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}
