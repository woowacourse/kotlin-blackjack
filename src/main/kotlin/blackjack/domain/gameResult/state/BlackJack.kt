package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class BlackJack<T : Participant>(override val participant: T) : State<T> {
    override val earnRate: Double
        get() = 1.5

    override fun compare(state: State<out Participant>): GameResult {
        if (state is BlackJack<out Participant>) return GameResult.DRAW
        return GameResult.WIN
    }
}
