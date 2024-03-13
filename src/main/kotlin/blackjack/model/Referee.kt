package blackjack.model

import blackjack.model.role.PlayerName

class Referee {
    fun judgeWinningResult(
        dealerSum: Int,
        playerResult: Map<PlayerName, Int>,
    ): PlayerWinning =
        PlayerWinning(
            playerResult.mapValues { (_, playerSum) ->
                determineGameResult(dealerSum, playerSum)
            },
        )

    private fun determineGameResult(
        dealerSum: Int,
        playerSum: Int,
    ): WinningResultStatus =
        when {
            playerSum > BLACK_JACK -> WinningResultStatus.DEFEAT
            dealerSum > BLACK_JACK -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }

    companion object {
        private const val BLACK_JACK = 21
    }
}
