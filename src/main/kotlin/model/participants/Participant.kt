package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo
import model.result.ResultType

abstract class Participant(participantState: ParticipantState, val wallet: Wallet) {
    var participantState: ParticipantState = participantState
        private set

    fun isBust() = participantState is ParticipantState.Bust

    fun isPlaying() = participantState is ParticipantState.Playing

    open fun judge(other: Participant): ResultType {
        val optimalPoint = participantState.hand.optimalPoint
        val otherOptimalPoint = other.participantState.hand.optimalPoint

        return when {
            optimalPoint > otherOptimalPoint -> ResultType.WIN
            optimalPoint == otherOptimalPoint -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    fun hit(card: Card) {
        participantState = participantState.hit(card)
    }
}
