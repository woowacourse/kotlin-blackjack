package blackjack.domain.state

import blackjack.domain.Hand

class Blackjack(
    override val hand: Hand,
) : Finished(hand) {
    override fun profit(profitMoney: Int): Int = hand.getProfitMoney(1.5).toInt()

    override fun canDrawCard(): Boolean = false
}
