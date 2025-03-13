package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.state.State
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class PlayerResults(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    private val playerResults: List<PlayerResult>

    init {
        playerResults =
            players.map {
                PlayerResult(it, judgePlayerResult(it))
            }
    }

    fun toList(): List<PlayerResult> {
        return playerResults.toList()
    }

    private fun judgePlayerResult(player: Player): GameResultState {
        val playerState = State.of(player)
        val dealerState = State.of(dealer)
        val result = playerState.compare(dealerState)
        return GameResultState(playerState, result)
    }
}
