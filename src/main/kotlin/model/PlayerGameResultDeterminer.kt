package model

import entity.GameResultType
import entity.GameResults

class PlayerGameResultDeterminer : GameResultDeterminer {
    override fun determine(player: User, users: List<User>): GameResults {
        val playerCardNumberSum = player.cardsNumberSum()
        val gameResults = mutableMapOf<GameResultType, Int>()
        val maxCardNumberSum = users.maxOf { it.cardsNumberSum() }
        if (playerCardNumberSum == maxCardNumberSum) gameResults[GameResultType.WIN] = 1
        else if (playerCardNumberSum < maxCardNumberSum) gameResults[GameResultType.LOSE] = 1
        return GameResults(gameResults)
    }
}
