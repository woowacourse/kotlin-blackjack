package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResults(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    var playerResults: List<PlayerResult>
        get() = field.toList()
        private set

    init {
        playerResults =
            players.map { player ->
                val status = judgePlayerResult(player)
                val profit =
                    when (status) {
                        GameResultStatus.PLAYER_WIN -> player.bettingAmount
                        GameResultStatus.PLAYER_LOSE -> player.bettingAmount * -1
                        GameResultStatus.DRAW -> 0
                        GameResultStatus.DEALER_BLACKJACK -> player.bettingAmount * -1
                        GameResultStatus.PLAYER_BLACKJACK -> (player.bettingAmount * 1.5).toInt()
                    }
                PlayerResult(player, profit)
            }
    }

    fun judgePlayerResult(player: Player): GameResultStatus {
        if (player.isBust()) return GameResultStatus.PLAYER_LOSE
        if (dealer.isBust()) return GameResultStatus.PLAYER_WIN
        if (player.isBlackJack() != dealer.isBlackJack()) {
            return if (player.isBlackJack()) GameResultStatus.PLAYER_BLACKJACK else GameResultStatus.DEALER_BLACKJACK
        }

        return when {
            dealer.totalSum > player.totalSum -> GameResultStatus.PLAYER_LOSE
            player.totalSum > dealer.totalSum -> GameResultStatus.PLAYER_WIN
            player.totalSum == dealer.totalSum -> GameResultStatus.DRAW
            else -> throw IllegalArgumentException()
        }
    }
}
