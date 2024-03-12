package blackjack.model

class GameStatistics(
    dealer: Dealer,
    players: List<Player>,
) {
    val playerStatistics: Map<String, GameResult> by lazy {
        players.associate { player ->
            player.name to dealer.judge(player)
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
}
