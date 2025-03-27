package blackjack.model.state

import blackjack.model.card.Hand

class Blackjack(
    override val hand: Hand,
) : Finish(hand) {
    override val profitRate: Float
        get() = BLACKJACK_PROFIT

    override fun profit(): Float = profitRate

    companion object {
        const val BLACKJACK_PROFIT = 1.5f
    }
}
