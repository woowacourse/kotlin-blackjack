package blackjack.model.winning

class DealerWinning(playerWinning: PlayerWinning) {
    fun getFinalResult(): Map<WinningResultStatus, Int> {
        return mapOf(
            WinningResultStatus.VICTORY to getVictoryCount(),
            WinningResultStatus.DEFEAT to getDefeatCount(),
            WinningResultStatus.PUSH to getPushCount(),
        )
    }

    private val playerWinningResult = playerWinning.result.values

    private fun getVictoryCount(): Int = playerWinningResult.filter { it == WinningResultStatus.DEFEAT }.size

    private fun getDefeatCount(): Int = playerWinningResult.filter { it == WinningResultStatus.VICTORY }.size

    private fun getPushCount(): Int = playerWinningResult.filter { it == WinningResultStatus.PUSH }.size
}
