package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>,
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

    private fun reverseGameResult(it: GameResult) =
        when (it) {
            GameResult.LOSE -> GameResult.WIN
            GameResult.WIN -> GameResult.LOSE
            GameResult.DRAW -> GameResult.DRAW
        }

    private fun judge(
        dealer: Dealer,
        player: Player,
    ): GameResult {
        return when {
            player.isBusted() -> GameResult.LOSE
            dealer.isBusted() -> GameResult.WIN
            player.isBlackJack() && dealer.isBlackJack() -> GameResult.DRAW
            player.isBlackJack() -> GameResult.WIN
            dealer.isBlackJack() -> GameResult.LOSE
            else -> compareScore(player.getCardSum(), dealer.getCardSum())
        }
    }

    private fun compareScore(
        playerScore: Int,
        dealerScore: Int,
    ): GameResult {
        return when {
            (playerScore > dealerScore) -> GameResult.WIN
            (playerScore < dealerScore) -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
