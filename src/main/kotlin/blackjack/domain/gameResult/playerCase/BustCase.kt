package blackjack.domain.gameResult.playerCase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class BustCase(val gameResult: GameResult) {

    WIN(GameResult.WIN),
    LOSE(GameResult.LOSE);

    companion object {

        fun valueOf(dealerCardsState: CardsState): BustCase {
            return when (dealerCardsState) {
                is CardsState.Bust -> WIN
                else -> LOSE
            }
        }
    }
}
