package blackjack.domain.gameResult.rule

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import blackjack.domain.score.Score

data class Stay<T : Participant>(override val participant: T) : BlackJackRule<T>() {
    override fun getEarnRate(gameResult: GameResult): Double {
        return gameResult.sign * 1.0
    }

    override fun compare(other: BlackJackRule<out Participant>): GameResult {
        if (other is BlackJack) return GameResult.LOSE
        if (other is Bust) return GameResult.WIN
        return Score(participant).compare(Score(other.participant))
    }
}
