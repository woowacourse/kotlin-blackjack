package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.Result
import blackjack.domain.participant.Participant
import java.lang.IllegalStateException

interface State {
    val totalSum: Int
    val earnRate: Double

    fun compare(state: State): Result {
        return when {
            state is BlackJack -> Result.LOSE
            totalSum > state.totalSum -> Result.WIN
            totalSum < state.totalSum -> Result.LOSE
            totalSum == state.totalSum -> Result.DRAW
            else -> throw IllegalStateException("비교할 수 없는 상태입니다")
        }
    }

    companion object {
        fun of(participant: Participant): State {
            if (participant.isBust()) return Bust(participant)
            return if (participant.isBlackJack()) {
                BlackJack(participant)
            } else {
                Stay(participant)
            }
        }
    }
}
