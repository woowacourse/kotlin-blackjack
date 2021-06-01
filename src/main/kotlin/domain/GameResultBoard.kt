package domain

class GameResultBoard(private val resultBoard: Map<Player, GameResult>) :
    Map<Player, GameResult> by resultBoard {

    fun dealerResult(): Map<GameResult, Int> {
        val results = resultBoard.values.toSet()

        return results.associate { it.reverse() to it.countInResults(resultBoard.values.toList()) }
    }

    companion object {
        fun of(players: Players, dealer: Dealer): GameResultBoard {
            val resultBoard = players.associateWith { GameResult.find(it, dealer) }
            return GameResultBoard(resultBoard)
        }
    }
}