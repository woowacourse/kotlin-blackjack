package domain.state

import domain.card.Cards
import domain.game.GameResultType
import domain.participant.BettingMoney

class Burst(hand: Cards, bettingMoney: BettingMoney) : Finished(hand, bettingMoney) {
    override fun getProfitRate(dealerState: State): Double {
        return bettingMoney.money * GameResultType.LOSE.profitRate
    }
}
