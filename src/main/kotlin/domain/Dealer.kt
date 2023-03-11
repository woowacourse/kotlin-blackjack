package domain

class Dealer(
    name: String = DEALER_NAME,
    cards: Cards
) : Player(name, cards) {

    fun isHit(): Boolean = (!cards.isOver(SUM_CONDITION))

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}
