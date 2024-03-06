package blackjack.model

class Judge(
    val dealerStat: Stat,
    val playerStats: List<Stat>,
) {
    fun getDealerResult(): DealerResult {
        var win = 0
        var draw = 0
        var loss = 0

        playerStats.forEach { player ->
            val dealerDifference = CRITERIA_NUMBER - dealerStat.total
            val playerDifference = CRITERIA_NUMBER - player.total

            when {
                dealerDifference < 0 && playerDifference < 0 -> draw++
                dealerDifference < 0 -> loss++
                playerDifference < 0 -> win++
                playerDifference < dealerDifference -> loss++
                dealerDifference < playerDifference -> win++
                else -> draw++
            }
        }

        return DealerResult(win, draw, loss)
    }

    companion object {
        private const val CRITERIA_NUMBER = 21
    }
}
