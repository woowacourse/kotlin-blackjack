package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo

sealed class ParticipantState(val hand: Hand) {
    abstract fun hit(card: Card): ParticipantState

    class Playing(hand: Hand) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)

            return when {
                hand.point > Hand.BUST_BOUND -> Bust(hand)
                hand.point < Hand.BUST_BOUND -> Playing(hand)
                else -> BlackJack(hand)
            }
        }
    }

    class Bust(hand: Hand) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return Bust(hand)
        }
    }

    class BlackJack(hand: Hand) : ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return BlackJack(hand)
        }
    }
}
