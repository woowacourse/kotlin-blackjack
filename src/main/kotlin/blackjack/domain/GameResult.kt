package blackjack.domain

class GameResult(private val dealer: Dealer, val players: List<Player>) {
    val dealerResult = DealerResult()
    private val playerResult: MutableList<PlayerResult> = mutableListOf()

    private fun updateResult(
        player: Player,
        status: GameResultStatus,
    ) {
        playerResult.add(PlayerResult(player, status))

        when (status) {
            GameResultStatus.PLAYER_WIN -> dealerResult.addLose()
            GameResultStatus.PLAYER_LOSE -> dealerResult.addWin()
            GameResultStatus.DRAW -> dealerResult.addDraw()
        }
    }

    fun getPlayerResult(player: Player): GameResultStatus {
        if (player.isBust()) return GameResultStatus.PLAYER_LOSE
        if (dealer.isBust()) return GameResultStatus.PLAYER_WIN
        return when {
            dealer.totalSum > player.totalSum -> GameResultStatus.PLAYER_LOSE
            player.totalSum > dealer.totalSum -> GameResultStatus.PLAYER_WIN
            player.totalSum == dealer.totalSum -> GameResultStatus.DRAW
            else -> throw IllegalArgumentException()
        }
    }

    fun getResult(): GameResult {
        players.forEach { player ->
            val playerResult = getPlayerResult(player)
            updateResult(player, playerResult)
        }
        return this
    }

    fun getAllPlayerResult(): List<PlayerResult> = playerResult.toList()
}
