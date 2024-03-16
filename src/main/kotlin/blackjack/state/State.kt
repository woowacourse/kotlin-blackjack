package blackjack.state

import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.ProfitRate
import blackjack.model.card.Card
import blackjack.model.card.Hand

sealed class State(val hand: Hand) {
    fun sumScore(): Int = hand.sum()

    class Running(hand: Hand) : State(hand) {
        fun hit(card: Card): State {
            val newHand = hand + card
            if (newHand.isBust()) return Finish.Bust(newHand)
            return Running(newHand)
        }

        fun stay(): Finish {
            if (hand.isBlackjack()) return Finish.BlackJack(hand)
            if (hand.isBust()) return Finish.Bust(hand)
            return Finish.Stop(hand)
        }
    }

    sealed class Finish(hand: Hand) : State(hand) {
        abstract val ratePoint: Int

        protected abstract fun compareRatePoint(otherPoint: Int): ProfitRate

        fun calculateProfit(
            betting: Betting,
            otherPoint: Int,
        ): Profit = Profit(betting, compareRatePoint(otherPoint))

        class Stop(hand: Hand) : Finish(hand) {
            override val ratePoint: Int
                get() = sumScore()

            init {
                require(hand.isBust().not())
                require(hand.isBlackjack().not())
            }

            override fun compareRatePoint(otherPoint: Int): ProfitRate =
                when {
                    ratePoint > otherPoint -> WIN_PROFIT_RATE
                    ratePoint == otherPoint -> DRAW_PROFIT_RATE
                    ratePoint < otherPoint -> LOSE_PROFIT_RATE
                    else -> throw IllegalArgumentException("예상치 못한 값 $otherPoint 이 들어옴")
                }

            companion object {
                private val WIN_PROFIT_RATE = ProfitRate(1.0)
                private val LOSE_PROFIT_RATE = ProfitRate(-1.0)
            }
        }

        class Bust(hand: Hand) : Finish(hand) {
            override val ratePoint: Int = BUST_RATE_POINT

            init {
                require(hand.isBust()) {
                    "${sumScore()} 는 $BLACKJACK_POINT 보다 커야 한다"
                }
            }

            override fun compareRatePoint(otherPoint: Int): ProfitRate = BUST_PROFIT_RATE
        }

        class BlackJack(hand: Hand) : Finish(hand) {
            override val ratePoint: Int = BLACKJACK_POINT

            init {
                require(hand.isBlackjack()) {
                    "${sumScore()} 는 $BLACKJACK_POINT 여야 한다"
                }
            }

            override fun compareRatePoint(otherPoint: Int): ProfitRate {
                if (otherPoint == BLACKJACK_POINT) return DRAW_PROFIT_RATE
                return BLACKJACK_PROFIT_RATE
            }
        }
    }

    protected companion object {
        val BLACKJACK_PROFIT_RATE = ProfitRate(1.5)
        const val BUST_RATE_POINT = 0
        val BUST_PROFIT_RATE = ProfitRate(-1)
        val DRAW_PROFIT_RATE = ProfitRate(0.0)
        const val BLACKJACK_POINT = 100
    }
}
