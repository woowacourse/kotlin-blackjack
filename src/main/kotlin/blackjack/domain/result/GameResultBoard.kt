package blackjack.domain.result

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player


class GameResultBoard(private val resultBoard: Map<Player, GameResult>) :
    Map<Player, GameResult> by resultBoard {

    fun dealerResult(): Map<GameResult, Int> {
        val results = resultBoard.values.toSet()

        return results.associate { it.reverse() to it.countInResults(resultBoard.values.toList()) }
    }

    companion object {
        fun of(players: List<Player>, dealer: Dealer): GameResultBoard {
            val resultBoard = players.associateWith { it.result(dealer.state.totalScore()) }
            return GameResultBoard(resultBoard)
        }

        fun ofProfit(players: List<Player>, dealer: Dealer): GameResultBoard {
            val resultBoard = players.associateWith { it.result(dealer.state.totalScore()) }
            return GameResultBoard(resultBoard)
        }
    }
}
