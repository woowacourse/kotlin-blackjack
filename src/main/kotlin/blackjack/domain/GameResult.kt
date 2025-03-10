package blackjack.domain

class GameResult(game: BlackJackGame) {
    private val playerResults: List<PlayerResult>
    private val dealer = game.dealer
    private val players = game.players

    init {
        playerResults =
            players.map { player ->
                val status = getPlayerResult(player)
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

    fun getAllPlayerResult(): List<PlayerResult> {
        return playerResults
    }
}
