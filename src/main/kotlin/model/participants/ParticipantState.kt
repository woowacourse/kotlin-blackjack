package model.participants

import model.card.Card
import model.participants.Hand.Companion.BLACKJACK_CARD_COUNT
import model.result.Point.Companion.compareTo
import model.result.Profit
import model.result.Profit.Companion.MINUS_ONE_TIMES
import model.result.Profit.Companion.ONE_AND_HALF_TIMES
import model.result.Profit.Companion.ONE_TIMES
import model.result.Profit.Companion.ZERO

sealed class ParticipantState(val hand: Hand) {
    abstract fun hit(card: Card): ParticipantState

    fun getProfit(
        money: Int,
        other: ParticipantState,
    ): Profit {
        return (money * getRate(other)).run { Profit(this) }
    }

    open fun getRate(other: ParticipantState): Double {
        return ZERO
    }

    class None(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)
            return Ready(hand)
        }
    }

    class Ready(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)
            return if (hand.isBlackjack()) BlackJack(hand) else Playing(hand)
        }
    }

    class Playing(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)

            return when {
                hand.point > Hand.BLACK_JACK_POINT -> Bust(hand)
                hand.point < Hand.BLACK_JACK_POINT -> Playing(hand)
                hand.cards.size == BLACKJACK_CARD_COUNT -> BlackJack(hand)
                else -> Playing(hand)
            }
        }

        override fun getRate(other: ParticipantState): Double {
            return when {
                other is Bust -> ONE_TIMES
                hand.optimalPoint > other.hand.optimalPoint -> ONE_TIMES
                hand.optimalPoint < other.hand.optimalPoint -> MINUS_ONE_TIMES
                else -> super.getRate(other)
            }
        }
    }

    class Bust(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return Bust(hand)
        }

        override fun getRate(other: ParticipantState): Double {
            return MINUS_ONE_TIMES
        }
    }

    class BlackJack(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return BlackJack(hand)
        }

        override fun getRate(other: ParticipantState): Double {
            return when {
                other is BlackJack -> ZERO
                else -> ONE_AND_HALF_TIMES
            }
        }
    }
}
