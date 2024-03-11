package blackjack.model

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
            playerSum > CardHandState.BLACKJACK.precondition -> WinningResultStatus.DEFEAT
            dealerSum > CardHandState.BLACKJACK.precondition -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
}
