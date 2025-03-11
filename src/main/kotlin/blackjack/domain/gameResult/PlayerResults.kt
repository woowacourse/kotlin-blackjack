package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class PlayerResults(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    private val playerResults: List<PlayerResult>

    init {
        playerResults =
            players.map { player ->
                val status = judgePlayerResult(player)
                PlayerResult(player, status)
            }
    }

    fun judgePlayerResult(player: Player): GameResultStatus {
        if (player.isBust()) return GameResultStatus.PLAYER_LOSE
        if (dealer.isBust()) return GameResultStatus.PLAYER_WIN
        return when {
            dealer.totalSum > player.totalSum -> GameResultStatus.PLAYER_LOSE
            player.totalSum > dealer.totalSum -> GameResultStatus.PLAYER_WIN
            player.totalSum == dealer.totalSum -> GameResultStatus.DRAW
            else -> throw IllegalArgumentException()
        }
    }

    fun toList(): List<PlayerResult> = playerResults.toList()

    fun countDealerWin(): Int {
        return playerResults.count { it.status == GameResultStatus.PLAYER_LOSE }
    }

    fun countDealerLose(): Int {
        return playerResults.count { it.status == GameResultStatus.PLAYER_WIN }
    }

    fun countDealerDraw(): Int {
        return playerResults.count { it.status == GameResultStatus.DRAW }
    }
}
