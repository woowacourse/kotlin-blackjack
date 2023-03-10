package blackjack.domain.gameResult.playerCase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class BustCase(
    val condition: (dealerCardsState: CardsState) -> Boolean,
    val gameResult: GameResult,
) {

    WIN(
        condition = { dealerCardsState -> dealerCardsState == CardsState.Bust },
        gameResult = GameResult.WIN
    ),
    LOSE(
        condition = { dealerCardsState -> dealerCardsState != CardsState.Bust },
        gameResult = GameResult.LOSE
    );

    companion object {

        fun valueOf(dealerCardsState: CardsState): BustCase? {
            return values().find { bustCase ->
                bustCase.condition(dealerCardsState)
            }
        }
    }
}
