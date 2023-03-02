package model

import entity.GameResultType
import entity.GameResults

class PlayerGameResultDeterminer {
    fun determine(dealer: User, players: List<User>): GameResults {
        val dealerCardNumberSum = dealer.cardsNumberSum()
        return GameResults(
            players.associate {
                val playerCardNumberSum = it.cardsNumberSum()
                if (playerCardNumberSum in (dealerCardNumberSum + 1)..21 || (playerCardNumberSum <= 21 && dealerCardNumberSum > 21)) Pair(it, GameResultType.WIN)
                else if (playerCardNumberSum == dealerCardNumberSum || (playerCardNumberSum > 21 && dealerCardNumberSum > 21)) Pair(it, GameResultType.DRAW)
                else Pair(it, GameResultType.LOSE)
            }
        )
    }
}
