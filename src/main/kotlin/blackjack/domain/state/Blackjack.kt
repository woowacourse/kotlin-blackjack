package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Money

class Blackjack(override val hand: Hand, override val bettingMoney: Money?) : Finished(hand, bettingMoney) {
    override val earningRate: Double = 1.5
}
