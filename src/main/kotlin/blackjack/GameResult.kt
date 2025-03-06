package blackjack

class DealerResult() {
    var win: Int = 0
        private set
    var lose: Int = 0
        private set
    var draw: Int = 0
        private set

    fun addWin() {
        win++
    }

    fun addLose() {
        lose++
    }

    fun addDraw() {
        draw++
    }
}

class GameResult(private val dealer: Dealer, val players: List<Player>) {
    private val dealerResult = DealerResult()
    private val playerResultStatus = mutableMapOf<Player, GameResultStatus>()

    private fun updateResult(
        player: Player,
        status: GameResultStatus,
    ) {
        playerResultStatus[player] = status

        when (status) {
            GameResultStatus.PLAYER_WIN -> dealerResult.addLose()
            GameResultStatus.PLAYER_LOSE -> dealerResult.addWin()
            GameResultStatus.DRAW -> dealerResult.addDraw()
        }
    }

    fun getResult(): GameResult {
        players.forEach { player ->
            val playerResult = dealer.getPlayerResult(player)
            updateResult(player, playerResult)
        }
        return this
    }
}
