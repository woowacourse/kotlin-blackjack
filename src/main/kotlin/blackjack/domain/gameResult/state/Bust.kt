package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class Bust(private val participant: Participant) : State {
    override val totalSum: Int
        get() = participant.totalSum
    override val earnRate: Double
        get() = 1.0

    override fun compare(state: State): GameResult {
        return GameResult.LOSE
    }
}
