package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import blackjack.domain.score.Score
import java.lang.IllegalStateException

data class Stay<T : Participant>(override val participant: T) : State<T> {
    override val earnRate: Double
        get() = 1.0

    override fun compare(state: State<out Participant>): GameResult {
        return when {
            state is BlackJack<out Participant> -> GameResult.LOSE
            Score(participant) > Score(state.participant) -> GameResult.WIN
            Score(participant) < Score(state.participant) -> GameResult.LOSE
            Score(participant).isEqualTo(Score(state.participant)) -> GameResult.DRAW
            else -> throw IllegalStateException("비교할 수 없는 상태입니다")
        }
    }
}
