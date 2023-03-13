package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.card.Hand

class Blackjack(override val hand: Hand, override val bettingMoney: Money?) : Finished(hand, bettingMoney) {
    override val earningRate: Double = 1.5
}
