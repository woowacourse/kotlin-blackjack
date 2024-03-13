package model.participants

import model.ParticipantState
import model.result.Point.Companion.compareTo
import model.result.ResultType

class Player(
    override var participantState: ParticipantState,
    override val participantName: ParticipantName =
        ParticipantName.fromInput(
            DEFAULT_NAME,
        ),
) : Participant(participantState, participantName) {
    override fun canHit(): Boolean = getPointWithAce() < BUST_BOUND

    override fun judge(other: Participant): ResultType {
        return when {
            participantState is ParticipantState.Bust -> ResultType.LOSE
            other.participantState is ParticipantState.Bust -> ResultType.WIN
            this.getPointWithAce() > other.getPointWithAce() -> ResultType.WIN
            this.getPointWithAce() == other.getPointWithAce() -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
