package blackjack.model.state

import blackjack.model.card.Hand

class Stay(
    override val hand: Hand,
) : Finish(hand) {
    override val profitRate: Float
        get() = STAY_PROFIT

    override fun profit(): Float = profitRate

    companion object {
        const val STAY_PROFIT = 1f
    }
}
