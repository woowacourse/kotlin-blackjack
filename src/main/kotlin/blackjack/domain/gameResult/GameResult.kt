package blackjack.domain.gameResult

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.playerCase.BlackJackCase
import blackjack.domain.gameResult.playerCase.BustCase
import blackjack.domain.gameResult.playerCase.RunningCase

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

        private const val EXCEPTION_CASE = "처리하지 못한 케이스입니다"

        fun valueOf(playerCardsState: CardsState, dealerCardsState: CardsState): GameResult = when (playerCardsState) {
            is CardsState.BlackJack -> BlackJackCase.valueOf(dealerCardsState)?.gameResult
            is CardsState.Bust -> BustCase.valueOf(dealerCardsState)?.gameResult
            is CardsState.Running -> RunningCase.valueOf(playerCardsState.score, dealerCardsState)?.gameResult
        } ?: throw java.lang.IllegalStateException(EXCEPTION_CASE)
    }
}
