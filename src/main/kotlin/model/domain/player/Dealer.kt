package model.domain.player

import model.domain.card.Hand
import model.domain.state.State
import model.domain.state.gameinprogress.Dealing
import model.tools.Money

class Dealer private constructor(
    name: String = DEALER_NAME,
) : Player(name) {
    override var state: State = Dealing(Hand())

    override fun betMoney(bettingMoney: Money) = money.bet(bettingMoney)

    fun calculateTotalMoney(userProfit: Int) = money.lose(userProfit)

    companion object {
        private const val DEALER_NAME = "딜러"
        fun from() = Dealer()
    }
}
