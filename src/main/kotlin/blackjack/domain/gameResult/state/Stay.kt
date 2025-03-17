package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import blackjack.domain.score.Score

data class Stay<T : Participant>(override val participant: T) : BlackJackRole<T> {
    override fun getEarnRate(gameResult: GameResult): Double {
        return gameResult.sign * 1.0
    }

    override fun compare(blackJackRole: BlackJackRole<out Participant>): GameResult {
        if (blackJackRole is BlackJack) return GameResult.LOSE
        return Score(participant).compare(Score(blackJackRole.participant))
    }
}
