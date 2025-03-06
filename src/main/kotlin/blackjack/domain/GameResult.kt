package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState

class GameResult(
    private val dealer: Dealer,
) {
    fun calculateWin(players: List<Player>): Map<Player, ResultState> {
        return players.associateWith { player -> ResultState.calculateWin(player, dealer) }
    }
}
