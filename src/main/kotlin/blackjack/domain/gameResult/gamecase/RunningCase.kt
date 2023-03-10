package blackjack.domain.gameResult.gamecase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class RunningCase(
    val condition: (playerScore: Int, dealerCardsState: CardsState) -> Boolean,
    val gameResult: GameResult,
) {

    DEALER_BLACKJACK_LOSE(
        condition = {_, dealerCardsState -> dealerCardsState == CardsState.BlackJack},
        gameResult = GameResult.LOSE
    ),
    DEALER_BUST_WIN(
        condition = { _, dealerCardsState -> dealerCardsState == CardsState.Bust },
        gameResult = GameResult.WIN
    ),
    WIN(
        condition = { playerScore, dealerCardsState ->
            dealerCardsState is CardsState.Running && playerScore > dealerCardsState.score
        },
        gameResult = GameResult.WIN
    ),
    LOSE(
        condition = { playerScore, dealerCardsState ->
            dealerCardsState is CardsState.Running && playerScore < dealerCardsState.score
        },
        gameResult = GameResult.LOSE
    ),
    DRAW(
        condition = { playerScore, dealerCardsState ->
            dealerCardsState is CardsState.Running && playerScore == dealerCardsState.score
        },
        gameResult = GameResult.DRAW
    );


    companion object{
        fun valueOf(playerScore: Int, dealerCardsState: CardsState): RunningCase? {

            return values().find { runningCase ->
                runningCase.condition(playerScore, dealerCardsState)
            }
        }
    }

}