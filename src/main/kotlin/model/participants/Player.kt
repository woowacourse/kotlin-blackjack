package model.participants

import model.card.Card
import model.result.Profit
import model.result.ResultType

class Player(
    participantState: ParticipantState,
    wallet: Wallet =
        Wallet(
            IdCard.fromInput(
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

    inline fun play(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
        onDraw: () -> Card,
    ) {
        while (isPlaying() && playByDecision(readDecision, showHand, onDraw)) ;
    }

    inline fun playByDecision(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
        onDraw: () -> Card,
    ): Boolean {
        val continueToPlay = readDecision(this)
        if (continueToPlay) hit(onDraw())
        showHand(this)
        return continueToPlay
    }

    fun judgeProfit(other: Participant): Profit {
        return participantState.getProfit(wallet.money.amount, other.participantState)
    }

    companion object {
        const val DEFAULT_NAME = "Player"
    }
}
