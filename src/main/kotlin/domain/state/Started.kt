package domain.state

import domain.card.Hand
import domain.money.Money

abstract class Started(protected val hand: Hand) : State {
    override fun getHandCards() = hand.value

    override fun profit(money: Money): Double {
        throw IllegalStateException("")
    }

    override fun stay(): State {
        return Stay(hand)
    }
}
