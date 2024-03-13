package model.participants

import model.ParticipantState
import model.card.Card
import model.result.Point.Companion.compareTo

class Player(
    override var participantState: ParticipantState,
    override val participantName: ParticipantName =
        ParticipantName.fromInput(
            DEFAULT_NAME
        ),
) : Participant(participantState, participantName) {
    override fun canHit(): Boolean = getPointWithAce() < BUST_BOUND

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
