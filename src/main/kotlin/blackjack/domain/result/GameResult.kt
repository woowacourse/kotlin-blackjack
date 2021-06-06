package blackjack.domain.result

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Score

enum class GameResult(val result: String, val compareStatus: (Score, Score) -> Boolean) {
    WIN("승", { playerScore, dealerScore -> playerScore > dealerScore }),
    STAND_OFF("무", { playerScore, dealerScore -> playerScore == dealerScore }),
    LOSE("패", { playerScore, dealerScore -> playerScore < dealerScore });

    fun reverse() = when (this) {
        WIN -> LOSE
        LOSE -> WIN
        else -> this
    }

    fun countInResults(results: List<GameResult>): Int {
        return results.filter { this == it }
            .count()
    }

    companion object {
        @JvmStatic
        fun find(playerScore: Score, dealerScore: Score): GameResult {
            if(dealerScore > Score.BLACKJACK_SCORE) {
                return WIN
            }
            return values().firstOrNull { it.compareStatus(playerScore, dealerScore) }
                ?: throw IllegalArgumentException("승패 결과를 찾을 수 없습니다.")
        }
    }
}
