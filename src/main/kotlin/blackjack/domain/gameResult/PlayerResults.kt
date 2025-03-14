package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.state.PlayerState
import blackjack.domain.gameResult.state.State
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class PlayerResults(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    private val playerResults: List<GameResultState>

    init {
        playerResults =
            players.map {
                judgePlayerResult(it)
            }
    }

    fun toList(): List<GameResultState> {
        return playerResults.toList()
    }

    private fun judgePlayerResult(player: Player): GameResultState {
        val playerState = PlayerState.of(player)
        val dealerState = State.of(dealer)
        val result = playerState.compare(dealerState)
        return GameResultState(playerState, result)
    }
}
