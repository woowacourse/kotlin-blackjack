package domain

class Dealer(name: String = DEALER_NAME, cards: MutableList<Card>) : Player(name, cards) {
    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
        fun create(cards: List<Card>) = Dealer(cards = cards.toMutableList())
    }
}
