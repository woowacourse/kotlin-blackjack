package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.score.ScoreCalculator

class GameResult(
    private val dealer: Dealer,
) {
    fun calculateWin(players: List<Player>): Map<Player, ResultState> {
        return players.associateWith { player ->
            val state =
                ResultState.calculateWin(
                    ScoreCalculator.calculate(player.hand),
                    ScoreCalculator.calculate(dealer.hand),
                )

            state
        }
    }
}
