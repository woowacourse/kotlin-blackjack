package blackjack.domain

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    private val dealerResult = DealerResult()
    private val playerResults: List<PlayerResult>

    init {
        playerResults =
            players.map { player ->
                val status = getPlayerResult(player)
                updateDealerResult(status)
                PlayerResult(player, status)
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

    private fun updateDealerResult(status: GameResultStatus) {
        when (status) {
            GameResultStatus.PLAYER_WIN -> dealerResult.addLose()
            GameResultStatus.PLAYER_LOSE -> dealerResult.addWin()
            GameResultStatus.DRAW -> dealerResult.addDraw()
        }
    }

    fun getAllPlayerResult(): List<PlayerResult> {
        return playerResults
    }

    fun getDealerResult(): DealerResult {
        return dealerResult
    }
}
