package blackjack.domain.gameResult

import blackjack.domain.card.Cards

enum class GameResult(val profitRate: Double) {

    BLACKJACK_WIN(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0);

    operator fun not(): GameResult = when (this) {
        BLACKJACK_WIN -> LOSE
        WIN -> LOSE
        LOSE -> WIN
        DRAW -> DRAW
    }

    companion object {
        private const val EXCEPTION_CASE = "[ERROR] 처리하지 못한 케이스입니다"

        fun valueOf(playerCards: Cards, dealerCards: Cards): GameResult = GameResultCase
            .values()
            .find { GameResultCase ->
                GameResultCase.condition(playerCards, dealerCards)
            }?.gameResult ?: throw IllegalStateException(EXCEPTION_CASE)
    }
}
