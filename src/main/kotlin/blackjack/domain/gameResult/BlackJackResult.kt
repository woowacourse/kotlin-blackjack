package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.state.BlackJackRole
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class BlackJackResult(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    val playerResults: List<PlayerResult>
        get() = field.toList()

    init {
        playerResults =
            players.map {
                judgePlayerResult(it)
            }
    }

    private fun judgePlayerResult(player: Player): PlayerResult {
        val playerState = BlackJackRole(player)
        val dealerState = BlackJackRole(dealer)
        val result = playerState.compare(dealerState)
        return PlayerResult(playerState, result)
    }
}
