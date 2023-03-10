package blackjack.domain.gameResult.gamecase

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

        fun valueOf(cardsState: CardsState): BustCase? {
            return values().find { bustCase ->
                bustCase.condition(cardsState)
            }
        }
    }
}
