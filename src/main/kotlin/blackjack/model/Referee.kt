package blackjack.model

class Referee {
    fun judgeWinningResult(
        dealerSum: Int,
        playerResult: Map<String, Int>,
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
            playerSum > 21 -> WinningResultStatus.DEFEAT
            dealerSum > 21 -> WinningResultStatus.VICTORY
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
}
