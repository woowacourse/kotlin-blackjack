package blackjack.domain.gameResult

import blackjack.domain.card.Cards
import blackjack.domain.card.CardsState

enum class GameResultCase(
    val condition: (playerCards: Cards, dealerCards: Cards) -> Boolean,
    val gameResult: GameResult,
) {

    BLACKJACK_WIN(
        condition = { playerCards, dealerCards ->
            playerCards.checkCardsState(CardsState.BlackJack) &&
                    !dealerCards.checkCardsState(CardsState.BlackJack)
        },
        gameResult = GameResult.BLACKJACK_WIN
    ),

    DEALER_BURST(
        condition = { _, dealerCards ->
            dealerCards.checkCardsState(CardsState.Burst)
        },
        gameResult = GameResult.WIN
    ),

    PLAYER_BURST(
        condition = { playerCards, _ ->
            playerCards.checkCardsState(CardsState.Burst)
        },
        gameResult = GameResult.LOSE
    ),

    DRAW(
        condition = { playerCards, dealerCards ->
            (playerCards.getTotalCardsScore() == dealerCards.getTotalCardsScore())
        },
        gameResult = GameResult.DRAW
    ),

    WIN(
        condition = { playerCards, dealerCards ->
            playerCards.getTotalCardsScore() > dealerCards.getTotalCardsScore()
        },
        gameResult = GameResult.WIN
    ),

    LOSE(
        condition = { playerCards, dealerCards ->
            playerCards.getTotalCardsScore() < dealerCards.getTotalCardsScore()
        },
        gameResult = GameResult.LOSE
    );
}
