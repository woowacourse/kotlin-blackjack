package blackjack.domain.gameResult.rule

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class Bust<T : Participant>(override val participant: T) : BlackJackRule<T>() {
    override fun getEarnRate(gameResult: GameResult): Double = -1.0

    override fun compare(other: BlackJackRule<out Participant>): GameResult {
        return GameResult.LOSE
    }
}
