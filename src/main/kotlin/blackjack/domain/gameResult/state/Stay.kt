package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import blackjack.domain.score.Score

data class Stay<T : Participant>(override val participant: T) : BlackJackRule<T> {
    override fun getEarnRate(gameResult: GameResult): Double {
        return gameResult.sign * 1.0
    }

    override fun compare(blackJackRule: BlackJackRule<out Participant>): GameResult {
        if (blackJackRule is BlackJack) return GameResult.LOSE
        return Score(participant).compare(Score(blackJackRule.participant))
    }
}
