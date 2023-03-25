package model.domain.player

import model.domain.card.Hand
import model.domain.state.State
import model.domain.state.gameinprogress.Dealing
import model.tools.Money

class User private constructor(
    name: String,
) : Player(name) {
    override var state: State = Dealing(Hand())
    override fun betMoney(bettingMoney: Money) {
        money.bet(bettingMoney)
    }

    fun lose() = money.earn(PAY_LOSE)

    fun draw() = money.earn(PAY_BACK)

    fun winnerWinnerChickenDinner() = money.earn(PAY_BONUS)

    fun win() = money.earn(PAY_WIN)

    companion object {
        private const val PAY_LOSE = -1.0
        private const val PAY_BACK = 0.0
        private const val PAY_BONUS = 0.5
        private const val PAY_WIN = 1.0
        fun from(name: String): User = User(name)
    }
}
