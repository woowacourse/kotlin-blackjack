package blackjack.domain.gameResult

private const val BLACK_JACK_SCORE = 21

enum class GameResultCondition(
    val gameResult: GameResult,
    val condition: (playerScore: Int, dealerScore: Int) -> Boolean
) {

    PLAYER_BURST_CONDITION(
        condition = { playerScore, _ -> playerScore > BLACK_JACK_SCORE },
        gameResult = GameResult.LOSE
    ),
    DEALER_BURST_CONDITION(
        condition = { playerScore, dealerScore -> dealerScore > BLACK_JACK_SCORE && playerScore <= BLACK_JACK_SCORE },
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
