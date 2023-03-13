package domain.state

import domain.card.Cards
import domain.participant.BettingMoney

abstract class Running(val hand: Cards, val bettingMoney: BettingMoney) : State {
    override fun bettingMoney(): BettingMoney {
        return bettingMoney
    }

    override fun cards(): Cards {
        return hand
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun stay(): State {
        return Stay(hand, bettingMoney)
    }

    override fun getProfitRate(dealerState: State): Double {
        throw IllegalStateException(PROFIT_RATE_ERROR)
    }

    companion object {
        private const val PROFIT_RATE_ERROR = "아직 게임이 진행중이라 수익률을 계산할 수 없어요!!"
    }
}
