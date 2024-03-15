package model.participants

import model.result.Point.Companion.compareTo
import model.result.ResultType

class Player(
    participantState: ParticipantState,
    wallet: Wallet =
        Wallet(
            ParticipantName.fromInput(
                DEFAULT_NAME,
            ),
            Money(),
        ),
) : Participant(participantState, wallet) {
    override fun judge(other: Participant): ResultType {
        return when {
            participantState is ParticipantState.Bust -> ResultType.LOSE
            other.participantState is ParticipantState.Bust -> ResultType.WIN
            participantState.hand.calculateOptimalPoint() > other.participantState.hand.calculateOptimalPoint() -> ResultType.WIN
            participantState.hand.calculateOptimalPoint() == other.participantState.hand.calculateOptimalPoint() -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
