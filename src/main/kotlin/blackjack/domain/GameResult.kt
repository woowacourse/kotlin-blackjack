package blackjack.domain

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
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
