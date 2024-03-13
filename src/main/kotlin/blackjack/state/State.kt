package blackjack.state

import blackjack.model.card.Card
import blackjack.model.card.Hand

sealed class State(val hand: Hand) {
    fun sumScore(): Int = hand.sum()

    abstract val ratePoint: Int

    class Running(hand: Hand) : State(hand) {
        override val ratePoint: Int
            get() = sumScore()

        fun hit(card: Card): State {
            val newHand = hand + card
            if (newHand.isBust()) return Finish.Bust(newHand)
            return Running(newHand)
        }

        fun stay(): State {
            if (hand.isBlackjack()) return Finish.BlackJack(hand)
            if (hand.isBust()) return Finish.Bust(hand)
            return Finish.Stop(hand)
        }
    }

    sealed class Finish(hand: Hand) : State(hand) {
        class Stop(hand: Hand) : Finish(hand) {
            override val ratePoint: Int
                get() = sumScore()

            init {
                require(hand.isBust().not())
                require(hand.isBlackjack().not())
            }
        }

        class Bust(hand: Hand) : Finish(hand) {
            override val ratePoint: Int = BUST_RATE_POINT

            init {
                require(hand.isBust()) {
                    "${sumScore()} 는 $BLACKJACK_POINT 보다 커야 한다"
                }
            }
        }

        class BlackJack(hand: Hand) : Finish(hand) {
            override val ratePoint: Int = BLACKJACK_POINT

            init {
                require(hand.isBlackjack()) {
                    "${sumScore()} 는 $BLACKJACK_POINT 여야 한다"
                }
            }
        }
    }

    companion object {
        const val BUST_RATE_POINT = 0
        const val BLACKJACK_POINT = 21
    }
}
