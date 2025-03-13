package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class BlackJack(private val participant: Participant) : State {
    override val totalSum: Int
        get() = participant.totalSum
    override val earnRate: Double
        get() = 1.5

    override fun compare(state: State): GameResult {
        if (state is BlackJack) return GameResult.DRAW
        return GameResult.WIN
    }
}
