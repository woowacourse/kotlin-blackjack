package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import java.lang.IllegalStateException

interface State {
    val totalSum: Int
    val earnRate: Double

    fun compare(state: State): GameResult {
        return when {
            state is BlackJack -> GameResult.LOSE
            totalSum > state.totalSum -> GameResult.WIN
            totalSum < state.totalSum -> GameResult.LOSE
            totalSum == state.totalSum -> GameResult.DRAW
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
