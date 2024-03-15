package model.participants

import model.card.Card
import model.result.Point.Companion.compareTo
import model.result.ResultType

abstract class Participant(open var participantState: ParticipantState, open var wallet: Wallet) {
    abstract fun judge(other: Participant): ResultType

    fun hit(card: Card) {
        participantState = participantState.hit(card)
    }
}
