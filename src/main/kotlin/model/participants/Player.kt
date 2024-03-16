package model.participants

import model.result.Profit
import model.result.ResultType

class Player(
    participantState: ParticipantState,
    wallet: Wallet =
        Wallet(
            ParticipantName.fromInput(
                DEFAULT_NAME,
            ),
        ),
) : Participant(participantState, wallet) {
    override fun judge(other: Participant): ResultType {
        return when {
            this.isBust() -> ResultType.LOSE
            other.isBust() -> ResultType.WIN
            else -> super.judge(other)
        }
    }

    fun judgeProfit(other: Participant): Profit {
        return participantState.getProfit(wallet.money.amount, other.participantState)
    }

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
