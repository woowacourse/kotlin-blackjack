package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>
) {
    val playerStatistics: Map<String, GameResult> by lazy {
        val dealerScore = dealer.getCardSum()
        players.associate { player ->
            val playerScore = player.getCardSum()
            player.name to judge(playerScore, dealerScore)
        }
    }

    val dealerStatistics: Map<GameResult, Int> by lazy {
        playerStatistics.values.map {
            when (it) {
                GameResult.`패` -> GameResult.`승`
                GameResult.`승` -> GameResult.`패`
                GameResult.`무` -> GameResult.`무`
            }
        }.groupingBy { it }.eachCount()
    }

    private fun judge(playerScore: Int, dealerScore: Int): GameResult {
        return when {
            (dealerScore < playerScore) -> {
                GameResult.`승`
            }

            (dealerScore == playerScore) -> {
                GameResult.`무`
            }

            else -> {
                GameResult.`패`
            }
        }
    }
}
