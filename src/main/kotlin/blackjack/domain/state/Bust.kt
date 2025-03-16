package blackjack.domain.state

import blackjack.domain.Hand

class Bust(
    override val hand: Hand,
) : Finished(hand) {
    override fun profit(profitMoney: Int): Int = (hand.money * -1.0).toInt()

    override fun canDrawCard(): Boolean = false
}
