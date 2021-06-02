package blackjack.domain.result

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player

enum class GameResult(val result: String, val compareStatus: (Int, Int) -> Boolean) {
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
        fun find(player: Player, dealer: Dealer): GameResult {
            return values().firstOrNull { it.compareStatus(player.score(), dealer.score()) }
                ?: throw IllegalArgumentException("승패 결과를 찾을 수 없습니다.")
        }
    }
}
