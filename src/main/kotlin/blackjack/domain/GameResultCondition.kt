package blackjack.domain

enum class GameResultCondition(
    val gameResult: GameResult,
    val condition: (playerScore: Int, dealerScore: Int) -> Boolean
) {

    PLAYER_BURST_CONDITION(
        condition = { playerScore, _ -> playerScore > BLACKJACK_SCORE },
        gameResult = GameResult.LOSE
    ),
    DEALER_BURST_CONDITION(
        condition = { playerScore, dealerScore -> dealerScore > 21 && playerScore <= 21 },
        gameResult = GameResult.WIN
    ),
    DRAW_CONDITION(
        condition = { playerScore, dealerScore -> playerScore == dealerScore },
        gameResult = GameResult.DRAW
    ),
    PLAYER_LOSE_CONDITION(
        condition = { playerScore, dealerScore -> playerScore < dealerScore },
        gameResult = GameResult.LOSE
    ),
    PLAYER_WIN_CONDITION(
        condition = { playerScore, dealerScore -> playerScore > dealerScore },
        gameResult = GameResult.WIN
    );

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
