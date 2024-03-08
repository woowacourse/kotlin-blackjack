package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>
) {
    val playerStatistics: Map<String, GameResult> by lazy {
        players.associate { player ->
            player.name to judge(dealer, player)
        }
    }

    val dealerStatistics: Map<GameResult, Int> by lazy {
        playerStatistics.values.map { gameResult ->
            reverseGameResult(gameResult)
        }.groupingBy { it }.eachCount()
    }

    private fun reverseGameResult(it: GameResult) = when (it) {
        GameResult.`패` -> GameResult.`승`
        GameResult.`승` -> GameResult.`패`
        GameResult.`무` -> GameResult.`무`
    }

    private fun judge(dealer: Dealer, player: Player): GameResult {
        return when {
            player.isBusted() -> GameResult.`패`
            dealer.isBusted() -> GameResult.`승`
            player.isBlackJack() && dealer.isBlackJack() -> GameResult.`무`
            player.isBlackJack() -> GameResult.`승`
            dealer.isBlackJack() -> GameResult.`패`
            else -> compareScore(player.getCardSum(), dealer.getCardSum())
        }
    }

    private fun compareScore(playerScore: Int, dealerScore: Int): GameResult {
        return when {
            (playerScore > dealerScore) -> GameResult.`승`
            (playerScore < dealerScore) -> GameResult.`패`
            else -> GameResult.`무`
        }
    }
}
