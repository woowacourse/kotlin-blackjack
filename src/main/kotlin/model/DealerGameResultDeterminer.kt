package model

import entity.GameResultType
import entity.GameResults

class DealerGameResultDeterminer : GameResultDeterminer {
    override fun determine(dealer: User, users: List<User>): GameResults {
        val dealerCardNumberSum = dealer.cardsNumberSum()
        val gameResults = mutableMapOf<GameResultType, Int>()

        users.forEach {
            val userCardNumberSum = it.cardsNumberSum()
            val type = if (userCardNumberSum > dealerCardNumberSum) GameResultType.LOSE
            else if (userCardNumberSum == dealerCardNumberSum) GameResultType.DRAW
            else GameResultType.WIN
            gameResults[type] = gameResults.getOrDefault(type, 0) + 1
        }
        return GameResults(gameResults)
    }
}
