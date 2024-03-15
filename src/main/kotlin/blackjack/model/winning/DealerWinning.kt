package blackjack.model.winning

class DealerWinning(private val playerWinning: PlayerWinning) {
    fun getVictoryCount(): Int = getFinalResult().getOrDefault(WinningResultStatus.VICTORY, 0)

    fun getDefeatCount(): Int = getFinalResult().getOrDefault(WinningResultStatus.DEFEAT, 0)

    fun getPushCount(): Int = getFinalResult().getOrDefault(WinningResultStatus.PUSH, 0)

    private fun getFinalResult(): Map<WinningResultStatus, Int> {
        return playerWinning.result.values.groupingBy { it.reverse() }
            .eachCount()
    }
}
