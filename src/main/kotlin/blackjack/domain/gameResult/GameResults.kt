package blackjack.domain.gameResult

import blackjack.domain.BlackJackGame
import blackjack.domain.gameResult.state.State
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResults(private val dealer: Dealer, players: List<Player>) {
    constructor(game: BlackJackGame) : this(game.dealer, game.players)

    var playerResults: List<PlayerResult>
        get() = field.toList()
        private set

//    init {
//        playerResults =
//            players.map { player ->
//                val resultState = judgePlayerResult(player)
//                val profit = (resultState.getEarnRate()*player.bettingAmount).toInt()
//                PlayerResult(player, profit)
//            }
//    }

    init {
        playerResults =
            players.map { player ->
                PlayerResult(player, judgePlayerResult(player))
            }
    }

    private fun judgePlayerResult(player: Player): ResultState {
        val playerState = State.of(player)
        val dealerState = State.of(dealer)
        val result = playerState.compare(dealerState)
        return ResultState(playerState, result)
    }
}
