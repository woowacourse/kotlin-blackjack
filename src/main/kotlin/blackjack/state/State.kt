package blackjack.state

import blackjack.model.Result

const val WIN_EARNING_RATE: Double = 1.0
const val BLACK_JACK_EARNING_RATE: Double = 1.5
const val DRAW_EARNING_RATE: Double = 0.0
const val LOSE_EARNING_RATE: Double = -1.0

sealed interface State {
    fun calculateEarningRate(result: Result): Double

    sealed interface Action : State {
        object Hit : Action {
            override fun calculateEarningRate(result: Result): Double {
                return when (result) {
                    Result.WIN -> WIN_EARNING_RATE
                    Result.DRAW -> DRAW_EARNING_RATE
                    Result.LOSE -> LOSE_EARNING_RATE
                }
            }
        }
    }

    sealed interface Finish : State {
        object Bust : Finish {
            override fun calculateEarningRate(result: Result): Double {
                return LOSE_EARNING_RATE
            }
        }

        object Stay : Finish {
            override fun calculateEarningRate(result: Result): Double {
                return when (result) {
                    Result.WIN -> WIN_EARNING_RATE
                    Result.DRAW -> DRAW_EARNING_RATE
                    Result.LOSE -> LOSE_EARNING_RATE
                }
            }
        }

        object BlackJack : Finish {
            override fun calculateEarningRate(result: Result): Double {
                return when (result) {
                    Result.WIN -> BLACK_JACK_EARNING_RATE
                    else -> DRAW_EARNING_RATE
                }
            }
        }
    }
}
