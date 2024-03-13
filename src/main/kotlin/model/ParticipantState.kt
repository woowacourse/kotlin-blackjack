package model

import model.card.Card
import model.participants.Hand
import model.result.Point.Companion.compareTo

sealed class ParticipantState(val hand: Hand) {
    abstract fun hit(card: Card): ParticipantState
    class Playing(hand: Hand): ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            hand.draw(card)
            return if(hand.point >= Hand.BUST_BOUND) Bust(hand) else Playing(hand)
        }
    }
    class Bust(hand: Hand): ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return Bust(hand)
        }
    }
    class BlackJack(hand: Hand): ParticipantState(hand) {
        override fun hit(card: Card): ParticipantState {
            return BlackJack(hand)
        }
    }
}