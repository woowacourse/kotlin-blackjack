package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo
import model.result.ResultType

abstract class Participant(open var participantState: ParticipantState, open var wallet: Wallet) {

    fun isBust() = participantState is ParticipantState.Bust

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
