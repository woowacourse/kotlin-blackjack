package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.state.PlayerState
import blackjack.domain.gameResult.state.State
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
        val playerState = PlayerState.of(player)
        val dealerState = State.of(dealer)
        val result = playerState.compare(dealerState)
        return PlayerResult(playerState, result)
    }
}
