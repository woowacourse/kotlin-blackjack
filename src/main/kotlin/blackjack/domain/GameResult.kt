package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState

class GameResult private constructor(val winStatus: Map<Player, ResultState>) {
    companion object {
        fun create(
            dealer: Dealer,
            players: List<Player>,
        ): GameResult {
            return GameResult(
                players.associateWith { player ->
                    ResultState.calculateWin(player, dealer)
                },
            )
        }
    }
}
