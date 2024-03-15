package model.participants

import model.result.Point.Companion.compareTo
import model.result.ResultType

class Player(
    participantState: ParticipantState,
    participantName: ParticipantName =
        ParticipantName.fromInput(
            DEFAULT_NAME,
        ),
) : Participant(participantState, participantName) {
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
