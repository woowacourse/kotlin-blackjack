enum class GameResultCondition(
    val gameResult: GameResult,
    val condition: (playerScore: Int, dealerScore: Int) -> Boolean
) {
    // TODO: Enum class내에 존재하는 상수는 어떻게 해야할까?
    PLAYER_BURST_CONDITION(
        condition = { playerScore, _ -> playerScore > 21 },
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
}
