package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.Result
import blackjack.domain.participant.Participant

data class Bust(private val participant: Participant) : State {
    override val totalSum: Int
        get() = participant.totalSum
    override val earnRate: Double
        get() = 1.0

    override fun compare(state: State): Result {
        return Result.LOSE
    }
}
