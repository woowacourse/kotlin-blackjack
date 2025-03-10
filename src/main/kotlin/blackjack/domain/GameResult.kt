package blackjack.domain

class GameResult(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    private val playerResults: List<PlayerResult>

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
