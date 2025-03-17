package blackjack.model

import blackjack.model.Profit.Companion.INITIAL_PROFIT
import kotlin.math.abs

class Dealer(
    name: String = DEFAULT_DEALER_NAME,
    hand: Hand = Hand(emptyList()),
) : Participant(name, hand) {
    override val openCard: List<Card>
        get() = listOf(hand.value[OPEN_CARD_INDEX])
    override var profit: Profit = Profit(INITIAL_PROFIT)

    override fun canHit(): Boolean = getScore() <= DEALER_HIT_SCORE

    override fun updateProfit(opponent: Participant) {
        val profitChange = abs(opponent.profit.value)

        profit =
            when {
                isBlackjack().not() && opponent.isBlackjack() -> Profit(profit.value - profitChange)
                isBlackjack() && opponent.isBlackjack().not() -> Profit(profit.value + profitChange)
                opponent.isBust() -> Profit(profit.value + profitChange)
                isBust() && opponent.isBust().not() -> Profit(profit.value - profitChange)
                getScore() > opponent.getScore() -> Profit(profit.value + profitChange)
                getScore() < opponent.getScore() -> Profit(profit.value - profitChange)
                else -> profit
            }
    }

    companion object {
        const val DEFAULT_DEALER_NAME = "딜러"
        private const val OPEN_CARD_INDEX = 0
        private const val DEALER_HIT_SCORE = 16
    }
}
