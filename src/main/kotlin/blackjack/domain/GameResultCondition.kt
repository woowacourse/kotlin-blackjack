package blackjack.domain

enum class GameResultCondition(
    val gameResult: GameResult,
    val scoreCondition: (playerScore: Int, dealerScore: Int) -> Boolean,
    val blackJackCondition: (isPlayerBlackJack: Boolean) -> Boolean
) {
    PLAYER_BURST_CONDITION(
        scoreCondition = { playerScore, _ -> playerScore > BlackJackReferee.BLACK_JACK_SCORE },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack or true },
        gameResult = GameResult.LOSE
    ),
    DEALER_BURST_CONDITION(
        scoreCondition = { _, dealerScore -> dealerScore > BlackJackReferee.BLACK_JACK_SCORE },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack or true },
        gameResult = GameResult.WIN
    ),
    DRAW_CONDITION(
        scoreCondition = { playerScore, dealerScore -> playerScore == dealerScore },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack or true },
        gameResult = GameResult.DRAW
    ),
    PLAYER_LOSE_CONDITION(
        scoreCondition = { playerScore, dealerScore -> playerScore < dealerScore },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack or true },
        gameResult = GameResult.LOSE
    ),
    PLAYER_BLACKJACK_CONDITION(
        scoreCondition = { playerScore, dealerScore -> playerScore > dealerScore },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack and true },
        gameResult = GameResult.BLACKJACK
    ),
    PLAYER_WIN_CONDITION(
        scoreCondition = { playerScore, dealerScore -> playerScore > dealerScore },
        blackJackCondition = { isPlayerBlackJack -> isPlayerBlackJack or true },
        gameResult = GameResult.WIN
    );
}
