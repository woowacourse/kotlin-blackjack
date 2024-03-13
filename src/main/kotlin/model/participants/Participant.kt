package model.participants

import model.ParticipantState
import model.card.Card
import model.result.Point
import model.result.Point.Companion.compareTo
import model.result.ResultType

abstract class Participant(open var participantState: ParticipantState, open val participantName: ParticipantName) {
    fun judge(other: Participant): ResultType {
        return when {
            participantState is ParticipantState.Bust -> ResultType.LOSE
            other.participantState is ParticipantState.Bust -> ResultType.WIN
            this.getPointWithAce() > other.getPointWithAce() -> ResultType.WIN
            this.getPointWithAce() == other.getPointWithAce() -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    fun hit(card: Card) {
        when (val currentState = participantState) {
            is ParticipantState.Playing -> {
                participantState = currentState.hit(card)
            }
            is ParticipantState.Bust -> {
            }
            is ParticipantState.BlackJack -> {
            }
        }
    }

    fun getPointWithAce(): Point {
        return if (participantState.hand.hasAce()) {
            decideAceValue()
        } else {
            participantState.hand.point
        }
    }

    private fun decideAceValue(): Point {
        val point = participantState.hand.point

        return when {
            point <= Card.ACE_ADDITIONAL_POINT -> point + Card.ACE_ADDITIONAL_POINT
            else -> point
        }
    }

    abstract fun canHit(): Boolean

    companion object {
        const val BUST_BOUND = 21
    }
}
