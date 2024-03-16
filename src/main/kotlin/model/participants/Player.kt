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
            this.isBust() -> ResultType.LOSE
            other.isBust() -> ResultType.WIN
            else -> super.judge(other)
        }
    }

    fun judgeProfit(other: Participant): Profit {
        return when {
            // TODO 간소화 필요
            participantState is ParticipantState.BlackJack && other.participantState is ParticipantState.BlackJack -> {
                0.0
            }
            participantState is ParticipantState.BlackJack -> {
                participantState.profit(wallet.money.amount)
            }
            participantState is ParticipantState.Bust -> {
                participantState.profit(wallet.money.amount)
            }
            other.participantState is ParticipantState.Bust -> {
                participantState.profit(wallet.money.amount)
            }
            participantState.hand.calculateOptimalPoint() == other.participantState.hand.calculateOptimalPoint() -> {
                0.0
            }
            participantState.hand.calculateOptimalPoint() >= other.participantState.hand.calculateOptimalPoint() -> {
                participantState.profit(wallet.money.amount)
            }
            else -> {
                -participantState.profit(wallet.money.amount)
            }
        }.run { Profit(this) }
    }

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
