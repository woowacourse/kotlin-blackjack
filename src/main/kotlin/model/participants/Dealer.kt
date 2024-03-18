package model.participants

import model.result.Point.Companion.compareTo
import model.result.ResultType

class Dealer(
    participantState: ParticipantState,
    wallet: Wallet =
        Wallet(
            IdCard.fromInput(
                DEFAULT_NAME,
            ),
        ),
) : Participant(participantState, wallet) {
    fun canHit(): Boolean = participantState.hand.point <= HIT_THRESHOLD

    override fun judge(other: Participant): ResultType {
        return when {
            other.isBust() -> ResultType.WIN
            this.isBust() -> ResultType.LOSE
            else -> super.judge(other)
        }
    }

    companion object {
        private const val HIT_THRESHOLD = 16
        private const val DEFAULT_NAME = "딜러"
    }
}
