package blackjack.model

class Referee {
    fun judgeWinningResult(
        dealerSum: Int,
        playerResult: Map<String, Int>,
    ): PlayerWinning {
        return PlayerWinning(
            playerResult.mapValues { (_, value) ->
                determineGameResult(dealerSum, value)
            },
        )
    }

    private fun determineGameResult(
        dealerSum: Int,
        playerSum: Int,
    ): WinningResultStatus {
        return when {
            dealerSum > playerSum -> WinningResultStatus.DEFEAT
            dealerSum == playerSum -> WinningResultStatus.DRAW
            playerSum > 21 -> WinningResultStatus.DEFEAT
            else -> WinningResultStatus.VICTORY
        }
    }
}
