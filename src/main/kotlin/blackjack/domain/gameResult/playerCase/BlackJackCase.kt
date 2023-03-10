package blackjack.domain.gameResult.playerCase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class BlackJackCase(
    val condition: (dealerCardsState: CardsState) -> Boolean,
    val gameResult: GameResult,
) {

    WIN(
        condition = { dealerCardsState ->
            dealerCardsState != CardsState.BlackJack
        },
        gameResult = GameResult.BLACKJACK_WIN
    ),
    DRAW(
        condition = { dealerCardsState ->
            dealerCardsState == CardsState.BlackJack
        },
        gameResult = GameResult.DRAW
    );

    companion object {

        fun valueOf(dealerCardsState: CardsState): BlackJackCase? {
            return values().find { blackJackCase ->
                blackJackCase.condition(dealerCardsState)
            }
        }
    }
}
