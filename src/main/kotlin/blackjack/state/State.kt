package blackjack.state

import blackjack.model.card.Card
import blackjack.model.card.Hand

sealed class State(val hand: Hand) {
    fun sumScore(): Int = hand.sumOptimized()

    class Running(hand: Hand) : State(hand) {
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
            init {
                require(hand.isBlackjack().not())
                require(hand.isBust().not())
            }
        }

        class Bust(hand: Hand) : Finish(hand) {
            init {
                check(sumScore() > BLACKJACK_POINT) {
                    "${sumScore()} 는 $BLACKJACK_POINT 보다 커야 한다"
                }
            }
        }

        class BlackJack(hand: Hand) : Finish(hand) {
            init {
                check(sumScore() == BLACKJACK_POINT) {
                    "${sumScore()} 는 $BLACKJACK_POINT 여야 한다"
                }
            }
        }
    }

    companion object {
        private const val MIN_POINT = 0
        private const val BLACKJACK_POINT = 21
    }
}
