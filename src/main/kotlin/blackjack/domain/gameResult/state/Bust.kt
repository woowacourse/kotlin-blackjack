package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class Bust<T : Participant>(override val participant: T) : State<T> {
    override val earnRate: Double
        get() = 1.0

    override fun compare(state: State<out Participant>): GameResult {
        return GameResult.LOSE
    }
}
