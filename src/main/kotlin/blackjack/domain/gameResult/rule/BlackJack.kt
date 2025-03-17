package blackjack.domain.gameResult.rule

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class BlackJack<T : Participant>(override val participant: T) : BlackJackRule<T>() {
    override fun getEarnRate(gameResult: GameResult): Double {
        return 1.5 * gameResult.sign
    }

    override fun compare(other: BlackJackRule<out Participant>): GameResult {
        if (other is BlackJack) return GameResult.DRAW
        return GameResult.WIN
    }
}
