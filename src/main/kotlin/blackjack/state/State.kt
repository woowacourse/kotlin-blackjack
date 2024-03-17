package blackjack.state

import blackjack.model.BettingMoney
import blackjack.model.Result

const val WIN_EARNING_RATE: Double = 1.0
const val BLACK_JACK_EARNING_RATE: Double = 1.5
const val DRAW_EARNING_RATE: Double = 0.0

sealed interface State {
    fun calculateProfit(
        balance: BettingMoney,
        result: Result,
    ): Double

    sealed interface Action : State {
        object Hit : Action {
            override fun calculateProfit(
                balance: BettingMoney,
                result: Result,
            ): Double {
                return when (result) {
                    Result.WIN -> balance * WIN_EARNING_RATE
                    Result.DRAW -> DRAW_EARNING_RATE
                    Result.LOSE -> -balance
                }
            }
        }
    }

    sealed interface Finish : State {
        object Bust : Finish {
            override fun calculateProfit(
                balance: BettingMoney,
                result: Result,
            ): Double {
                return -balance
            }
        }

        object Stay : Finish {
            override fun calculateProfit(
                balance: BettingMoney,
                result: Result,
            ): Double {
                return when (result) {
                    Result.WIN -> balance * WIN_EARNING_RATE
                    Result.DRAW -> DRAW_EARNING_RATE
                    Result.LOSE -> -balance
                }
            }
        }

        object BlackJack : Finish {
            override fun calculateProfit(
                balance: BettingMoney,
                result: Result,
            ): Double {
                return when (result) {
                    Result.WIN -> balance * BLACK_JACK_EARNING_RATE
                    else -> DRAW_EARNING_RATE
                }
            }
        }
    }
}
