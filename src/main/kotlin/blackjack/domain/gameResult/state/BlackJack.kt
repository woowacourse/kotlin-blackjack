package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class BlackJack<T : Participant>(override val participant: T) : State<T> {
    override fun getEarnRate(gameResult: GameResult): Double {
        return 1.5 * gameResult.sign
    }

    override fun compare(state: State<out Participant>): GameResult {
        if (state is BlackJack) return GameResult.DRAW
        return GameResult.WIN
    }
}
