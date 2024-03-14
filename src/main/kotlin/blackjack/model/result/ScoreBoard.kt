package blackjack.model.result

import blackjack.model.role.PlayerName

class ScoreBoard(private val record: Map<PlayerName, Int>) {
    fun calculatePlayerWinning(dealerScore: Int): PlayerWinning =
        PlayerWinning(
            record.mapValues { (_, playerScore) ->
                determineGameResult(dealerScore, playerScore)
            },
        )

    private fun determineGameResult(
        dealerScore: Int,
        playerScore: Int,
    ): WinningResultStatus =
        when {
            playerScore > 21 -> WinningResultStatus.DEFEAT
            dealerScore > 21 -> WinningResultStatus.VICTORY
            dealerScore > playerScore -> WinningResultStatus.DEFEAT
            dealerScore == playerScore -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
}
