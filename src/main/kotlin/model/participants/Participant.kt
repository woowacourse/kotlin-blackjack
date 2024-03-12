package model.participants

import model.card.Card
import model.card.Deck
import model.result.Point
import model.result.Point.Companion.compareTo

abstract class Participant(open val hand: Hand, open val participantName: ParticipantName) {
    fun getPointIncludingAce(): Point {
        return if (hand.hasAce()) {
            decideAceValue()
        } else {
            hand.point
        }
    }

    private fun decideAceValue(): Point {
        val point = hand.point
        return if (point <= Card.ACE_ADDITIONAL_POINT) {
            point + Card.ACE_ADDITIONAL_POINT
        } else {
            point
        }
    }

    abstract fun hit(deck: Deck): Boolean

    abstract fun canHit(): Boolean

    companion object {
        const val BUST_BOUND = 21
    }
}
