package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo
import model.result.ResultType

abstract class Participant(open var participantState: ParticipantState, open var wallet: Wallet) {

    fun isBust() = participantState is ParticipantState.Bust

    open fun judge(other: Participant): ResultType {
        return when {
            participantState.hand.calculateOptimalPoint() > other.participantState.hand.calculateOptimalPoint() -> ResultType.WIN
            participantState.hand.calculateOptimalPoint() == other.participantState.hand.calculateOptimalPoint() -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    fun hit(card: Card) {
        participantState = participantState.hit(card)
    }
}
