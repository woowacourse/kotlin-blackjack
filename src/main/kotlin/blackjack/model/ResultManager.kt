package blackjack.model

class ResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun playerResults(): Map<String, WinningResult> =
        players.value.associate { player ->
            player.name to WinningResult.from(player.score(), dealer.score())
        }

    fun dealerResult(): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()
        val playerScores = players.value.map { player -> player.score() }

        playerScores.forEach { playerScore ->
            val winningResult = WinningResult.from(dealer.score(), playerScore)
            result[winningResult] = result.getOrDefault(
                winningResult,
                INITIAL_SCORE,
            ) + ADDITIONAL_RESULT_COUNT
        }

        return result.toMap()
    }

    companion object {
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
