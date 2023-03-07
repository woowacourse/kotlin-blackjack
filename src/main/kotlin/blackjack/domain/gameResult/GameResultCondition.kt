package blackjack.domain.gameResult

import blackjack.domain.card.Cards
import blackjack.domain.card.CardsState

enum class GameResultCondition(
    val condition: (playerCards: Cards, dealerCards: Cards) -> Boolean,
    val gameResult: GameResult,
) {

    BLACKJACK_WIN(
        condition = { playerCards, dealerCards ->
            playerCards.state == CardsState.BlackJack &&
                    dealerCards.state != CardsState.BlackJack
        },
        gameResult = GameResult.BLACKJACK_WIN
    ),

    DEALER_BURST(
        condition = { _, dealerCards ->
            dealerCards.state == CardsState.Burst
        },
        gameResult = GameResult.WIN
    ),

    PLAYER_BURST(
        condition = { playerCards, _ ->
            playerCards.state == CardsState.Burst
        },
        gameResult = GameResult.LOSE
    ),

    DRAW(
        condition = { playerCards, dealerCards ->
            (playerCards.getTotalCardsScore() == dealerCards.getTotalCardsScore())
        },
        gameResult = GameResult.LOSE
    ),

    WIN(
        condition = { playerCards, dealerCards ->
            playerCards.getTotalCardsScore() > dealerCards.getTotalCardsScore()
        },
        gameResult = GameResult.DRAW
    ),

    LOSE(
        condition = { playerCards, dealerCards ->
            playerCards.getTotalCardsScore() < dealerCards.getTotalCardsScore()
        },
        gameResult = GameResult.WIN
    );
}
