package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant

data class Bust<T : Participant>(override val participant: T) : BlackJackRole<T> {
    override fun getEarnRate(gameResult: GameResult): Double = -1.0

    override fun compare(blackJackRole: BlackJackRole<out Participant>): GameResult {
        return GameResult.LOSE
    }
}
