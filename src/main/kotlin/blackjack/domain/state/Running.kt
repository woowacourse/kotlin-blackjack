package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Money

abstract class Running(override val hand: Hand, override val bettingMoney: Money?) : State {

    override fun betting(money: Money): State = throw IllegalStateException(ALREADY_PASSED_BET_STATE)

    override fun stay(): State = Stay(hand, bettingMoney)

    override fun getProfit(): Double = throw IllegalStateException(CANT_CALCULATE_PROFIT)

    companion object {
        const val MIN_HAND_SIZE = 2
        private const val ALREADY_PASSED_BET_STATE = "이미 베팅 상태를 지났습니다."
        private const val CANT_CALCULATE_PROFIT = "게임이 끝나기 전까지는 수익을 계산할 수 없습니다."
    }
}
