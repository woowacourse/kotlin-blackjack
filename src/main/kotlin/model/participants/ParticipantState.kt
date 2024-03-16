package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo

sealed class ParticipantState(val hand: Hand) {
    open val rate: Double = DEFAULT_RATE

    abstract fun hit(card: Card): ParticipantState

    fun profit(money: Int): Double {
        return money * rate
    }

    class None(hand: Hand = Hand()) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)
            return Ready(hand)
        }
    }

    class Ready(hand: Hand) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)
            return if (hand.isBlackjack()) BlackJack(hand) else Playing(hand)
        }
    }

    class Playing(hand: Hand) : ParticipantState(hand) {
        override val rate: Double = ONE_TIMES

        override fun hit(card: Card): ParticipantState {
            hand.draw(card)

            return when {
                hand.point > Hand.BLACK_JACK_POINT -> Bust(hand)
                hand.point < Hand.BLACK_JACK_POINT -> Playing(hand)
                hand.cards.size == 2 -> BlackJack(hand)
                else -> Playing(hand)
            }
        }
    }

    class Bust(hand: Hand) : ParticipantState(hand) {
        override val rate: Double = MINUS_ONE_TIMES

        override fun hit(card: Card): ParticipantState {
            return Bust(hand)
        }
    }

    class BlackJack(hand: Hand) : ParticipantState(hand) {
        override val rate: Double = ONE_AND_HALF_TIMES

        override fun hit(card: Card): ParticipantState {
            return BlackJack(hand)
        }
    }

    companion object {
        private const val DEFAULT_RATE = 0.0
        private const val ONE_TIMES = 1.0
        private const val MINUS_ONE_TIMES = -1.0
        private const val ONE_AND_HALF_TIMES = 1.5
    }
}
