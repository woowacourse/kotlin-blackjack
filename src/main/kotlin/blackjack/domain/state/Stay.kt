package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.card.Hand

class Stay(override val hand: Hand, override val bettingMoney: Money?) : Finished(hand, bettingMoney) {
    override val earningRate: Double = 1.0
}
