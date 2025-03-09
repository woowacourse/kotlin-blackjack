package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState

class GameResult(dealer: Dealer, players: List<Player>) {
    val winStatus: Map<Player, ResultState> =
        players.associateWith { player ->
            ResultState.calculateWin(player.calculateScore(), dealer.calculateScore())
        }

    fun countByResultState(): Map<ResultState, Int> {
        return winStatus.values.groupingBy { it }.eachCount()
    }

    private fun Person.calculateScore(): Int = ScoreCalculator.calculate(this.cards())
}
