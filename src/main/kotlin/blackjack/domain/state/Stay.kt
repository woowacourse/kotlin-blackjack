package blackjack.domain.state

import blackjack.domain.Hand

class Stay(
    override val hand: Hand,
) : Finished(hand) {
    override fun profit(profitMoney: Int): Int = (hand.money * 1)

    override fun canDrawCard(): Boolean = false
}
