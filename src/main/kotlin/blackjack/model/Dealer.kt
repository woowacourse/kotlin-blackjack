package blackjack.model

class Dealer(
    name: String = DEFAULT_DEALER_NAME,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    override val openCard: List<Card>
        get() = listOf(hand.value[OPEN_CARD_INDEX])
    override val money: Money = Money(INITIAL_MONEY_VALUE)

    override fun canHit(): Boolean = getScore() <= DEALER_HIT_SCORE

    override fun updateProfit(opponent: Participant) {
        when {
            isBlackjack().not() && opponent.isBlackjack() -> money.minus(opponent.money)
            isBlackjack() && opponent.isBlackjack().not() -> money.plus(opponent.money)
            opponent.isBust() -> money.plus(opponent.money)
            isBust() && opponent.isBust().not() -> money.minus(opponent.money)
            getScore() > opponent.getScore() -> money.plus(opponent.money)
            getScore() < opponent.getScore() -> money.minus(opponent.money)
        }
    }

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        private const val OPEN_CARD_INDEX = 0
        private const val INITIAL_MONEY_VALUE = 0
        private const val DEALER_HIT_SCORE = 16
    }
}
