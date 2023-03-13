package domain.state

import domain.card.Cards
import domain.game.GameResultType
import domain.participant.BettingMoney

class Stay(hand: Cards, bettingMoney: BettingMoney) : Finished(hand, bettingMoney) {
    val score: Int
        get() = hand.getScore().getValue()

    override fun getProfitRate(dealerState: State): Double {
        return when (dealerState) {
            is Burst -> bettingMoney.money * GameResultType.WIN.profitRate
            is BlackJack -> bettingMoney.money * GameResultType.LOSE.profitRate
            is Stay -> bettingMoney.money * compareScore(dealerState.score)
            else -> throw IllegalArgumentException(INVALID_DEALER_STATE_ERROR)
        }
    }

    private fun compareScore(dealerScore: Int): Double {
        return when {
            dealerScore > score -> GameResultType.LOSE.profitRate
            dealerScore < score -> GameResultType.WIN.profitRate
            else -> GameResultType.DRAW.profitRate
        }
    }

    companion object {
        private const val INVALID_DEALER_STATE_ERROR = "잘못된 상태값이 들어왔어요!"
    }
}
